package com.brandjunhoe.productservice.cart.presentation.dto

import com.brandjunhoe.productservice.cart.domain.Cart
import com.brandjunhoe.productservice.product.domain.ProductCode
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 * Create by DJH on 2022/03/18.
 */
class ReqCartSaveDTO(

    @field:NotNull
    val usrId: UUID,

    @field:NotBlank
    val productCode: String,

    @field:NotBlank
    val itemCode: String,

    @field:NotNull
    val quantity: Int

) {
    fun toEntity():
        Cart = Cart(this.usrId, ProductCode(productCode), itemCode, quantity)
}
