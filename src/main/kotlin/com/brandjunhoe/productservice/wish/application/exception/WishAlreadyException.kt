package com.brandjunhoe.productservice.wish.application.exception

import com.brandjunhoe.productservice.common.code.ErrorCode
import com.brandjunhoe.productservice.common.exception.BadRequestException

/**
 * Create by DJH on 2022/05/26.
 */
class WishAlreadyException : BadRequestException(ErrorCode.WISH_ALREADY)