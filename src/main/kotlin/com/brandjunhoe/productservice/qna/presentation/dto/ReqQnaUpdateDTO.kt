package com.brandjunhoe.productservice.qna.presentation.dto

import com.brandjunhoe.productservice.qna.domain.enums.QnaTypeEnum
import java.util.*
import javax.validation.constraints.NotBlank

/**
 * Create by DJH on 2022/04/05.
 */
class ReqQnaUpdateDTO(

    @NotBlank
    val usrId: UUID,

    val type: QnaTypeEnum? = null,

    val secretState: Boolean? = null,

    val title: String? = null,

    val question: String? = null


)