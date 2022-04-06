package com.brandjunhoe.productservice.qna.presentation

import com.brandjunhoe.productservice.common.page.ReqPageDTO
import com.brandjunhoe.productservice.common.page.PageDTO
import com.brandjunhoe.productservice.common.response.CommonResponse
import com.brandjunhoe.productservice.qna.application.QnaService
import com.brandjunhoe.productservice.qna.application.dto.QnaDTO
import com.brandjunhoe.productservice.qna.presentation.dto.ReqQnaSaveDTO
import com.brandjunhoe.productservice.qna.presentation.dto.ReqQnaUpdateDTO
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid
import javax.validation.constraints.NotBlank

/**
 * Create by DJH on 2022/04/05.
 */
@RestController
@RequestMapping("/qna")
class QnaController(val qnaService: QnaService) {


    @GetMapping
    fun findAllQnas(pageRequest: ReqPageDTO): CommonResponse<PageDTO<List<QnaDTO>>> =
        CommonResponse(qnaService.findAll(pageRequest.getPageable()))


    @PostMapping("/{productCode}")
    fun saveQna(@PathVariable productCode: String, @RequestBody request: ReqQnaSaveDTO): CommonResponse<Unit> {
        qnaService.save(productCode, request)
        return CommonResponse()
    }


    @PatchMapping("/{productCode}")
    fun updateQna(@PathVariable productCode: String, @RequestBody request: ReqQnaUpdateDTO): CommonResponse<Unit> {
        qnaService.update(productCode, request)
        return CommonResponse()
    }


    @DeleteMapping("/{id}")
    fun deleteQna(@PathVariable @Valid @NotBlank id: UUID) {
        qnaService.delete(id)
    }

}