package com.brandjunhoe.productservice.review.domain

import com.brandjunhoe.productservice.common.domain.DateColumnEntity
import com.brandjunhoe.productservice.product.domain.ItemCode
import com.brandjunhoe.productservice.product.domain.ProductCode
import com.brandjunhoe.productservice.review.domain.enums.ReviewTypeEnum
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Where
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "review")
@Where(clause = "deldate IS NOT NULL")
class Review(

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    val id: UUID = UUID.randomUUID(),

    @Embedded
    val productCode: ProductCode,

    @Embedded
    val itemCode: ItemCode,

    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "enum", nullable = false)
    val type: ReviewTypeEnum,

    @Column(name = "score", nullable = false)
    val score: Int,

    @Column(name = "contents", nullable = false)
    val contents: String,

    @Column(name = "mileage")
    val mileage: Int = 0,

    @ElementCollection
    @CollectionTable(name = "review_image", joinColumns = [JoinColumn(name = "review_id")])
    val images: List<ReviewImage> = listOf()

) : DateColumnEntity() {


}

