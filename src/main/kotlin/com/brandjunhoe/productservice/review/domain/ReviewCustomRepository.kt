package com.brandjunhoe.productservice.review.domain

import com.brandjunhoe.productservice.review.application.dto.ReviewTotalDTO

/**
 * Create by DJH on 2022/03/29
 */
interface ReviewCustomRepository {
    fun findByReviewSummary(productCode: String) : ReviewTotalDTO
}