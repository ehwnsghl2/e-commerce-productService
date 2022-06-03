package com.brandjunhoe.productservice.review.application.exception

import com.brandjunhoe.productservice.common.code.ErrorCode
import com.brandjunhoe.productservice.common.exception.BadRequestException

/**
 * Create by DJH on 2022/06/02.
 */
class ReviewPhotoIntputValueException : BadRequestException(ErrorCode.REVIEW_PHOTO_INPUT_VALUE)