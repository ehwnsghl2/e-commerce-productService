package com.brandjunhoe.productservice.review.domain

/**
 * Create by DJH on 2022/03/29
 */
interface ReviewCustomRepository {
    fun findByTotalReviewScoreAvgAndCount(productCode: String)
}