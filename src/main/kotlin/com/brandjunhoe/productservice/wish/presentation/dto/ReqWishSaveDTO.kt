package com.brandjunhoe.productservice.wish.presentation.dto

import com.brandjunhoe.productservice.product.domain.ProductCode
import com.brandjunhoe.productservice.wish.domain.Wish
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 * Create by DJH on 2022/03/16.
 */
data class ReqWishSaveDTO(

    @field:NotNull
    val usrId: UUID,

    @field:NotBlank
    val productCode: String

) {
    fun toEntity(): Wish = Wish(usrId, ProductCode(productCode))
}
