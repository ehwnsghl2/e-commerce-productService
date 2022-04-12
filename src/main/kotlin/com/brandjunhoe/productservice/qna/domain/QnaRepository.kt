package com.brandjunhoe.productservice.qna.domain

import com.brandjunhoe.productservice.product.domain.ProductCode
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

/**
 * Create by DJH on 2022/04/05.
 */
interface QnaRepository {
    fun findById(id: UUID): Qna?
    fun findAll(pageable: Pageable): Page<Qna>
    fun save(qna: Qna): Qna
    fun findByUsrIdAndProductCode(usrId: UUID, productCode: ProductCode): Qna?
}