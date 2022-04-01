package com.brandjunhoe.productservice.review.application

import com.brandjunhoe.productservice.common.exception.BadRequestException
import com.brandjunhoe.productservice.common.page.ResPageDTO
import com.brandjunhoe.productservice.common.page.TotalPageDTO
import com.brandjunhoe.productservice.review.application.dto.ReviewDTO
import com.brandjunhoe.productservice.review.domain.ReviewRepository
import com.brandjunhoe.productservice.review.presentation.dto.ReqReviewSaveDTO
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

/**
 * Create by DJH on 2022/03/21.
 */
@Service
class ReviewService(val reviewRepository: ReviewRepository) {


    fun findAll(pageRequest: PageRequest): ResPageDTO<List<ReviewDTO>> {
        val reviews = reviewRepository.findAll(pageRequest)

        return ResPageDTO(
            TotalPageDTO(reviews.number, reviews.totalPages, reviews.totalElements),
            reviews.content.map { ReviewDTO(it.type, it.score, it.contents, it.images) }
        )
    }

    fun save(request: ReqReviewSaveDTO) {

        if (reviewRepository.exsistsByOrderProductCode(request.orderProductCode))
            throw BadRequestException("already writed review")

        // 포토리뷰인 경우 이미지 업로드

        // 마일리지 정책 조회 하여 적립금 부여


        request.toEntity(0)

        // 상품 리뷰 갱신



    }


}