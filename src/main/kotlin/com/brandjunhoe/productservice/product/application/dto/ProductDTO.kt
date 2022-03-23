package com.brandjunhoe.productservice.product.application.dto

/**
 * Create by DJH on 2022/03/22.
 */
class ProductDTO(
    val productCode: String,
    val name: String,
    val sellingPrice: Int,
    val sellingState: Boolean,
    val productImage: String? = null
)
