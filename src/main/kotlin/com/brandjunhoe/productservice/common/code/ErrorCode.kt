package com.brandjunhoe.productservice.common.code

import org.springframework.http.HttpStatus


enum class ErrorCode(val status: Int, val code: Int?, val message: String) {


    // 4XX
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), 400,"잘못된 요청입니다."),

    UNAUTHORIZED(HttpStatus.UNAUTHORIZED.value(), 401,"Unauthorized"),
    FORBIDDEN(HttpStatus.FORBIDDEN.value(), 403,"Forbidden"),
    DATA_NOT_FOUND(HttpStatus.NOT_FOUND.value(), 404,"Not found"),

    QNA_ANSWER_NOT_CHANGE(HttpStatus.BAD_REQUEST.value(), 101,"qna not change was answer"),
    REVIEW_PHOTO_INPUT_VALUE(HttpStatus.BAD_REQUEST.value(), 102,"user password not maching"),
    REAVIEW_ALREADY(HttpStatus.BAD_REQUEST.value(), 103,"review was already"),
    WISH_ALREADY(HttpStatus.BAD_REQUEST.value(), 104,"wish was already"),

    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED.value(), 405,"해당 메서드는 해당되지 않습니다."),
    UNSUPPORTED_MEDIA_TYPE(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), 415,"지원하지 않는 HTTP 메서드입니다"),
    CONFLICT(HttpStatus.CONFLICT.value(), 409,"이미 사용 중인 데이터 입니다."),

    //5XX
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), 500,"서버에러가 발생했습니다. 서버 관리자에게 문의하세요")


}