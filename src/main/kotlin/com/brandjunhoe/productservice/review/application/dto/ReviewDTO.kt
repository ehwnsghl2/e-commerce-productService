package com.brandjunhoe.productservice.review.application.dto

import com.brandjunhoe.productservice.review.domain.Review
import com.brandjunhoe.productservice.review.domain.ReviewImage
import com.brandjunhoe.productservice.review.domain.enums.ReviewTypeEnum

/**
 * Create by DJH on 2022/03/28.
 */
class ReviewDTO(

    val type: ReviewTypeEnum,

    val score: Int,

    val contents: String,

    val images: List<ReviewImage>? = null

) {
    constructor(review: Review) : this(review.type, review.score, review.contents, review.images)
}
