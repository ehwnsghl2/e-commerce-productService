package com.brandjunhoe.productservice.review.domain

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

/**
 * Create by DJH on 2022/03/21.
 */
interface ReviewRepository {
    fun findAll(pageable: Pageable): Page<Review>
    fun existsByOrderProductCode(orderProductCode: String): Boolean
    fun save(review: Review): Review
}