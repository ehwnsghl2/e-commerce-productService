package com.brandjunhoe.productservice.review.application

import com.brandjunhoe.productservice.common.exception.DataNotFoundException
import com.brandjunhoe.productservice.product.application.dto.ItemOptionValueDTO
import com.brandjunhoe.productservice.product.application.dto.ItemOptionsDTO
import com.brandjunhoe.productservice.product.domain.ItemRepository
import com.brandjunhoe.productservice.product.domain.ProductCode
import com.brandjunhoe.productservice.product.domain.ProductRepository
import com.brandjunhoe.productservice.product.presentation.dto.ResProductDetailDTO
import com.brandjunhoe.productservice.review.domain.ReviewRepository
import org.springframework.stereotype.Service

/**
 * Create by DJH on 2022/03/21.
 */
@Service
class ReviewService(val reviewRepository: ReviewRepository) {


    fun findAll() {
        reviewRepository.findAll()
    }


}