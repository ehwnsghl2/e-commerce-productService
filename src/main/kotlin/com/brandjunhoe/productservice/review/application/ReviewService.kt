package com.brandjunhoe.productservice.review.application

import com.brandjunhoe.productservice.client.OrderImplClient
import com.brandjunhoe.productservice.common.calculator.rate
import com.brandjunhoe.productservice.common.page.PageDTO
import com.brandjunhoe.productservice.common.page.TotalPageDTO
import com.brandjunhoe.productservice.config.ReviewSaveProperties
import com.brandjunhoe.productservice.product.domain.ProductCode
import com.brandjunhoe.productservice.review.application.dto.ReviewDTO
import com.brandjunhoe.productservice.review.application.exception.ReviewAlreadyException
import com.brandjunhoe.productservice.review.application.exception.ReviewNotFoundException
import com.brandjunhoe.productservice.review.domain.Review
import com.brandjunhoe.productservice.review.domain.ReviewCustomRepository
import com.brandjunhoe.productservice.review.domain.ReviewRepository
import com.brandjunhoe.productservice.review.domain.enums.ReviewTypeEnum
import com.brandjunhoe.productservice.review.domain.event.ProductReviewUpdateEvent
import com.brandjunhoe.productservice.review.presentation.dto.ReqReviewSaveDTO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

/**
 * Create by DJH on 2022/03/21.
 */
@Service
class ReviewService(
    private val reviewRepository: ReviewRepository,
    private val reviewCustomRepository: ReviewCustomRepository,
    private val orderClient: OrderImplClient,
    private val reviewSaveProperties: ReviewSaveProperties,
    private val applicationEventPublisher: ApplicationEventPublisher
) {


    fun findAll(pageRequest: PageRequest): PageDTO<List<ReviewDTO>> {
        val reviews = reviewRepository.findAll(pageRequest)

        return PageDTO(
            TotalPageDTO(reviews.number, reviews.totalPages, reviews.totalElements),
            reviews.content.map { ReviewDTO(it) }
        )
    }

    fun findByOne(id: UUID): ReviewDTO {
        val review = findById(id)
        return ReviewDTO(review.type, review.score, review.contents)
    }

    fun save(request: ReqReviewSaveDTO) {

        if (reviewRepository.existsByOrderProductCode(request.orderProductCode))
            throw ReviewAlreadyException()

        if (request.type == ReviewTypeEnum.PHOTO) {

        }

        val mileageSaveRate = reviewSaveProperties.rate(request.type)

        val orderProduct = orderClient.findByOrderProduct(request.orderProductCode)

        val saveMileage = rate(orderProduct.amount, mileageSaveRate)

        reviewRepository.save(request.toEntity(saveMileage))

        // 상품 리뷰 갱신
        GlobalScope.launch { updateProductReviewSummary(request.productCode) }

    }

    @Transactional
    fun delete(id: UUID) {
        val review = findById(id)
        review.delete()
    }


    private fun updateProductReviewSummary(productCode: String) {
        val reviews = reviewCustomRepository.findByReviewSummary(productCode)

        applicationEventPublisher.publishEvent(
            ProductReviewUpdateEvent(
                ProductCode(productCode),
                reviews.count,
                reviews.scoreAvg
            )
        )
    }


    private fun findById(id: UUID): Review = reviewRepository.findById(id)
        ?: throw ReviewNotFoundException()


}