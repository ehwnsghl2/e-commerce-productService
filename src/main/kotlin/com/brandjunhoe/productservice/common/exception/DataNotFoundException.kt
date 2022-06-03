package com.brandjunhoe.productservice.common.exception

import com.brandjunhoe.productservice.common.code.ErrorCode


open class DataNotFoundException : RuntimeException() {
    val errorCode: ErrorCode = ErrorCode.DATA_NOT_FOUND
}