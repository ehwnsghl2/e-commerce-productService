package com.brandjunhoe.productservice.cart.application.dto

import java.util.*

/**
 * Create by DJH on 2022/04/15.
 */
class CartDTO(

    val id: UUID,

    val productCode: String,

    val productImagePath: String,

    val productName: String,

    val retailPrice: Int,

    val sellingPrice: Int,

    val sellingState: Boolean,

    val discountRate: Int? = null,

    val discountPrice: Int? = null,

    val soldOutState: Boolean

)