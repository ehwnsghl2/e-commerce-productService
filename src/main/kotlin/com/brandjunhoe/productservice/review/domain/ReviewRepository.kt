package com.brandjunhoe.productservice.review.domain

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

/**
 * Create by DJH on 2022/03/21.
 */
interface ReviewRepository {
    fun findAll(pageRequest: PageRequest): Page<Review>
    fun exsistsByOrderProductCode(orderProductCode: String): Boolean
    fun save(review: Review): Review
}