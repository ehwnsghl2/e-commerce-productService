package com.brandjunhoe.productservice.review.domain.event

import com.brandjunhoe.productservice.product.domain.ProductCode

/**
 * Create by DJH on 2022/04/01.
 */
class ProductReviewUpdateEvent(
    val productCode: ProductCode,
    val reviewCount: Int,
    val reviewRating: Double
)