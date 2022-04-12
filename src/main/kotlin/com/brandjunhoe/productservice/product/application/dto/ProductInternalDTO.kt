package com.brandjunhoe.productservice.product.application.dto

import com.brandjunhoe.productservice.product.domain.Product
import com.brandjunhoe.productservice.product.domain.enums.ProductGradeLimitEnum
import com.brandjunhoe.productservice.product.domain.enums.ProductTypeEnum

/**
 * Create by DJH on 2022/04/06.
 */
data class ProductInternalDTO(

    val productCode: String,

    val imagePath: String,

    val name: String,

    val type: ProductTypeEnum,

    val supplierPrice: Int = 0,

    val retailPrice: Int,

    val sellingPrice: Int,

    val displayState: Boolean,

    val sellingState: Boolean,

    val discountRate: Int? = null,

    val discountPrice: Int? = null,

    val gradeLimit: ProductGradeLimitEnum,

    val soldOutState: Boolean,

    val gradeSaleState: Boolean


) {

    constructor(product: Product) : this(
        product.productCode.productCode,
        product.mainImage.path.toString(),
        product.name,
        product.type,
        product.supplierPrice,
        product.retailPrice,
        product.sellingPrice,
        product.displayState,
        product.sellingState,
        product.discountRate,
        product.discountPrice,
        product.gradeLimit,
        product.soldOutState,
        product.gradeSaleState
    )

}
