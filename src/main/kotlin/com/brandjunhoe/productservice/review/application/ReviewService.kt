package com.brandjunhoe.productservice.review.application

import com.brandjunhoe.productservice.client.OrderImplClient
import com.brandjunhoe.productservice.common.calculator.rate
import com.brandjunhoe.productservice.common.exception.BadRequestException
import com.brandjunhoe.productservice.common.exception.DataNotFoundException
import com.brandjunhoe.productservice.common.page.ResPageDTO
import com.brandjunhoe.productservice.common.page.TotalPageDTO
import com.brandjunhoe.productservice.config.ReviewSaveProperties
import com.brandjunhoe.productservice.product.domain.ProductCode
import com.brandjunhoe.productservice.review.application.dto.ReviewDTO
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


    fun findAll(pageRequest: PageRequest): ResPageDTO<List<ReviewDTO>> {
        val reviews = reviewRepository.findAll(pageRequest)

        return ResPageDTO(
            TotalPageDTO(reviews.number, reviews.totalPages, reviews.totalElements),
            reviews.content.map { ReviewDTO(it.type, it.score, it.contents, it.images) }
        )
    }

    fun save(request: ReqReviewSaveDTO) {

        if (reviewRepository.existsByOrderProductCode(request.orderProductCode))
            throw BadRequestException("already writed review")

        // 포토리뷰인 경우 이미지 업로드
        if (request.type == ReviewTypeEnum.PHOTO) {

        }

        // 마일리지 정책 조회 하여 적립금 부여
        val rate = reviewSaveProperties.rate(request.type)

        val orderProduct = orderClient.findByOrderProduct(request.orderProductCode)

        val saveMileage = rate(orderProduct.amount, rate)


        request.toEntity(saveMileage)

        // 상품 리뷰 갱신
        GlobalScope.launch { productReviewUpdate(request.productCode) }

    }

    @Transactional
    fun delete(id: UUID) {
        val review = findById(id)
        review.delete()
    }


    private fun productReviewUpdate(productCode: String) {
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
        ?: throw DataNotFoundException("review not found")


}