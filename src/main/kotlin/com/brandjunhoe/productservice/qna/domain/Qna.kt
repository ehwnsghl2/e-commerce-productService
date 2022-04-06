package com.brandjunhoe.productservice.qna.domain

import com.brandjunhoe.productservice.common.domain.DateDeleteColumnEntity
import com.brandjunhoe.productservice.product.domain.ProductCode
import com.brandjunhoe.productservice.qna.domain.enums.QnaTypeEnum
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Where
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "qna")
@Where(clause = "deldate IS NOT NULL")
class Qna(

    @Column(name = "usr_id", nullable = false)
    val usrId: UUID,

    @Embedded
    val productCode: ProductCode,

    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "enum", nullable = false)
    var type: QnaTypeEnum,

    @Column(name = "secret_state", nullable = false)
    var secretState: Boolean,

    @Column(name = "title", length = 100, nullable = false)
    var title: String,

    @Column(name = "question", length = 255, nullable = false)
    var question: String,

    @Column(name = "answer", length = 255)
    val answer: String? = null,

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    val id: UUID = UUID.randomUUID()

) : DateDeleteColumnEntity() {


    fun update(type: QnaTypeEnum?, secretState: Boolean?, title: String?, question: String?) {
        type?.let { this.type = it }
        secretState?.let { this.secretState = it }
        title?.let { this.title = it }
        question?.let { this.question = it }
    }


}

