package com.brandjunhoe.productservice.review.domain

import com.brandjunhoe.productservice.common.domain.DateColumnEntity
import com.brandjunhoe.productservice.common.domain.DateDeleteColumnEntity
import com.brandjunhoe.productservice.product.domain.ItemCode
import com.brandjunhoe.productservice.product.domain.ProductCode
import com.brandjunhoe.productservice.review.domain.enums.ReviewTypeEnum
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Where
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "review")
@Where(clause = "deldate IS NULL")
class Review(

    @Column(name = "usr_id", nullable = false)
    val usrId: UUID,

    @Column(name = "order_product_code", nullable = false)
    val orderProductCode: String,

    @Column(name = "product_code", nullable = false)
    val productCode: String,

    @Column(name = "item_code", nullable = false)
    val itemCode: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "enum", nullable = false)
    val type: ReviewTypeEnum,

    @Column(name = "score", nullable = false)
    val score: Int,

    @Column(name = "contents", nullable = false)
    val contents: String,

    @Column(name = "mileage")
    val mileage: Int,

    @ElementCollection
    @CollectionTable(name = "review_image", joinColumns = [JoinColumn(name = "review_id")])
    val images: List<ReviewImage>? = null,

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    val id: UUID = UUID.randomUUID()

) : DateDeleteColumnEntity() {

    @Version
    var version: Long? = null


}

