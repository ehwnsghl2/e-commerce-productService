package com.brandjunhoe.productservice.product.application.dto

import com.brandjunhoe.productservice.product.domain.Product

/**
 * Create by DJH on 2022/03/25.
 */
class ProductSearchDTO(

    val productCode: String,

    val image: String? = null,

    val name: String,

    val discountRate: Int? = null,

    val discountPrice: Int? = null,

    val sellingPrice: Int,

    val sellingState: Boolean,

    val soldOutState: Boolean,

    val reviewCount: Int? = null,

    val reviewRating: Float? = null

    // 예상 적립금 추가 필요

    // 배송정책(무배, 수량 무배) 추가 필요


) {

    constructor(product: Product) : this(
        product.productCode.productCode,
        product.mainImage.path,
        product.name,
        product.discountRate,
        product.discountPrice,
        product.sellingPrice,
        product.sellingState,
        product.soldOutState,
        product.reviewCount,
        product.reviewRating
    )

}
