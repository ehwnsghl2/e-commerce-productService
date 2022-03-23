package com.brandjunhoe.productservice.review.domain

/**
 * Create by DJH on 2022/03/21.
 */
interface ReviewRepository {
    fun findAll(): List<Review>
}