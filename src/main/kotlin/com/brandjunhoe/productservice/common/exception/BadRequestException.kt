package com.brandjunhoe.productservice.common.exception

import com.brandjunhoe.productservice.common.code.ErrorCode


open class BadRequestException(val errorCode: ErrorCode) : RuntimeException()