package com.brandjunhoe.productservice.qna.presentation.dto

import com.brandjunhoe.productservice.product.domain.ProductCode
import com.brandjunhoe.productservice.qna.domain.Qna
import com.brandjunhoe.productservice.qna.domain.enums.QnaTypeEnum
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 * Create by DJH on 2022/04/05.
 */
class ReqQnaSaveDTO(

    @NotBlank
    val usrId: UUID,

    @NotNull
    val type: QnaTypeEnum,

    @NotNull
    val secretState: Boolean,

    @NotBlank
    val title: String,

    @NotBlank
    val question: String


) {

    fun toEntity(productCode: String): Qna =
        Qna(usrId, ProductCode(productCode), type, secretState, title, question)

}