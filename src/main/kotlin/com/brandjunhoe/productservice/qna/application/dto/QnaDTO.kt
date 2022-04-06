package com.brandjunhoe.productservice.qna.application.dto

import com.brandjunhoe.productservice.qna.domain.Qna
import com.brandjunhoe.productservice.qna.domain.enums.QnaTypeEnum

/**
 * Create by DJH on 2022/03/28.
 */
class QnaDTO(

    val type: QnaTypeEnum,

    val secretState: Boolean,

    val title: String,

    val question: String,

    val answer: String

) {
    constructor(qna: Qna) : this(qna.type, qna.secretState, qna.title, qna.question, qna.answer.toString())
}
