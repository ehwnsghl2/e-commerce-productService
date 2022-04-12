package com.brandjunhoe.productservice.qna.infra

import com.brandjunhoe.productservice.qna.domain.Qna
import com.brandjunhoe.productservice.qna.domain.QnaRepository
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Create by DJH on 2022/04/05.
 */
interface QnaJpaRepository : JpaRepository<Qna, Long>, QnaRepository {

}