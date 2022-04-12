package com.brandjunhoe.productservice.qna.application

import com.brandjunhoe.productservice.common.exception.BadRequestException
import com.brandjunhoe.productservice.common.exception.DataNotFoundException
import com.brandjunhoe.productservice.common.page.PageDTO
import com.brandjunhoe.productservice.common.page.TotalPageDTO
import com.brandjunhoe.productservice.product.domain.ProductCode
import com.brandjunhoe.productservice.qna.application.dto.QnaDTO
import com.brandjunhoe.productservice.qna.domain.Qna
import com.brandjunhoe.productservice.qna.domain.QnaRepository
import com.brandjunhoe.productservice.qna.presentation.dto.ReqQnaSaveDTO
import com.brandjunhoe.productservice.qna.presentation.dto.ReqQnaUpdateDTO
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

/**
 * Create by DJH on 2022/04/05.
 */
@Service
class QnaService(private val qnaRepository: QnaRepository) {

    fun findAll(pageRequest: PageRequest): PageDTO<List<QnaDTO>> {
        val reviews = qnaRepository.findAll(pageRequest)

        return PageDTO(
            TotalPageDTO(reviews.number, reviews.totalPages, reviews.totalElements),
            reviews.content.map { QnaDTO(it) }
        )
    }

    fun save(productCode: String, request: ReqQnaSaveDTO) {
        qnaRepository.save(request.toEntity(productCode))
    }

    @Transactional
    fun update(productCode: String, request: ReqQnaUpdateDTO) {
        val qna = findByUsrIdAndProductCode(request.usrId, productCode)

        if (qna.answer != null) throw BadRequestException("qna already answer")

        qna.update(request.type, request.secretState, request.title, request.question)
    }

    @Transactional
    fun delete(id: UUID) {
        val review = findById(id)
        review.delete()
    }

    private fun findById(id: UUID): Qna = qnaRepository.findById(id)
        ?: throw DataNotFoundException("qna not found")

    private fun findByUsrIdAndProductCode(usrID: UUID, productCode: String): Qna =
        qnaRepository.findByUsrIdAndProductCode(usrID, ProductCode(productCode))
            ?: throw DataNotFoundException("qna not found")

}