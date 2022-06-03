package com.brandjunhoe.productservice.qna.application.exception

import com.brandjunhoe.productservice.common.code.ErrorCode
import com.brandjunhoe.productservice.common.exception.BadRequestException

/**
 * Create by DJH on 2022/06/02.
 */
class QnaAnswerNotChangeException : BadRequestException(ErrorCode.QNA_ANSWER_NOT_CHANGE)