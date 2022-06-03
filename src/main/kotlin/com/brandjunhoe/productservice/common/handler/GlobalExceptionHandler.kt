package com.brandjunhoe.productservice.common.handler

import com.brandjunhoe.productservice.common.code.ErrorException
import com.brandjunhoe.productservice.common.code.ErrorCode
import com.brandjunhoe.productservice.common.exception.BadRequestException
import com.brandjunhoe.productservice.common.exception.DataNotFoundException
import com.brandjunhoe.productservice.common.response.CommonResponse
import org.apache.http.auth.AuthenticationException
import org.springframework.beans.BeanInstantiationException
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.multipart.MaxUploadSizeExceededException
import java.io.IOException
import java.util.*
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class GlobalExceptionHandler {

    @ExceptionHandler(
        value = [
            // HTTP 요청 에러
            HttpMessageNotReadableException::class,
            // 지원되지 않는 HTTP METHOD 에러 핸들러
            HttpRequestMethodNotSupportedException::class,
            // 파일이 용량 제한 에러
            MaxUploadSizeExceededException::class,
            // Parameter Validation Error
            BindException::class,
            // Parameter Validation Error
            MethodArgumentNotValidException::class,
            // 인자값 형식 에러
            IllegalArgumentException::class,
            // Kotlin non-null 파라메터 예외 처리
            BeanInstantiationException::class,
            // JWT 토큰 인증정보 확인 예외 처리
            AuthenticationException::class,
            // 권한 에러
            AccessDeniedException::class,
            // FeignClient 통신 에러
            javax.websocket.DecodeException::class,
            // 잘못된 파라메터 타입
            MethodArgumentTypeMismatchException::class,
            // 입출력 예외 처리
            IOException::class,
            // 에러
            Exception::class
        ]
    )
    @Throws(Exception::class)
    fun commonExcetpion(e: Exception, request: HttpServletRequest, webRequest: WebRequest): CommonResponse<Unit> {

        val errorCode = when (e::class.java.simpleName) {
            // validate 에러
            ErrorException.BindException.name,
            ErrorException.MethodArgumentNotValidException.name,
            ErrorException.HttpMessageNotReadableException.name,
            ErrorException.HttpRequestMethodNotSupportedException.name,
            ErrorException.IllegalArgumentException.name,
            ErrorException.FormValidationException.name,
             // 인증 실패
            ErrorException.AuthenticationException.name -> ErrorCode.UNAUTHORIZED
            // 권한 거부
            ErrorException.AccessDeniedException.name -> ErrorCode.FORBIDDEN
            // 서버 에러
            else -> ErrorCode.INTERNAL_SERVER_ERROR
        }

        return CommonResponse(errorCode.status, errorCode.code, errorCode.message)

    }


    @ExceptionHandler(value = [BadRequestException::class])
    fun BadRequestExcetpion(e: BadRequestException): CommonResponse<Unit> =
        CommonResponse(e.errorCode.status, e.errorCode.code, e.errorCode.message)

    @ExceptionHandler(value = [DataNotFoundException::class])
    fun DataNotFoundExcetpion(e: DataNotFoundException): CommonResponse<Unit> =
        CommonResponse(e.errorCode.status, e.errorCode.code, e.errorCode.message)


}