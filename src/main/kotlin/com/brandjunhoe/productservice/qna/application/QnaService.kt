package com.brandjunhoe.productservice.qna.application

import com.brandjunhoe.productservice.common.page.PageDTO
import com.brandjunhoe.productservice.common.page.TotalPageDTO
import com.brandjunhoe.productservice.product.application.exception.ProductNotFoundException
import com.brandjunhoe.productservice.product.domain.ProductCode
import com.brandjunhoe.productservice.product.domain.ProductRepository
import com.brandjunhoe.productservice.qna.application.dto.QnaDTO
import com.brandjunhoe.productservice.qna.application.exception.QnaAnswerNotChangeException
import com.brandjunhoe.productservice.qna.application.exception.QnaNotFoundException
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
class QnaService(
    private val productRepository: ProductRepository,
    private val qnaRepository: QnaRepository
) {

    fun findAll(pageRequest: PageRequest): PageDTO<List<QnaDTO>> {
        val reviews = qnaRepository.findAll(pageRequest)

        return PageDTO(
            TotalPageDTO(reviews.number, reviews.totalPages, reviews.totalElements),
            reviews.content.map { QnaDTO(it) }
        )
    }

    fun save(request: ReqQnaSaveDTO) {

        productRepository.findByProductCode(ProductCode(request.productCode))
            ?: throw ProductNotFoundException()

        qnaRepository.save(request.toEntity())
    }

    @Transactional
    fun update(id: UUID, request: ReqQnaUpdateDTO) {
        val qna = findById(id)

        if (qna.answer != null) throw QnaAnswerNotChangeException()

        qna.update(request.type, request.secretState, request.title, request.question)

    }

    @Transactional
    fun delete(id: UUID) {
        val qna = findById(id)
        qna.delete()
    }

    private fun findById(id: UUID): Qna = qnaRepository.findById(id)
        ?: throw QnaNotFoundException()

}