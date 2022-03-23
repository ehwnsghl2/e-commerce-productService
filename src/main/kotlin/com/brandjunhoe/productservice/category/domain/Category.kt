package com.brandjunhoe.productservice.category.domain

import com.brandjunhoe.productservice.common.domain.DateColumnEntity
import com.brandjunhoe.productservice.product.domain.enums.ProductGradeLimitEnum
import com.brandjunhoe.productservice.product.domain.enums.ProductTypeEnum
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.Where
import javax.persistence.*

@Entity
@Table(name = "category")
class Category(

    @EmbeddedId
    val categoryCode: CategoryCode,

    @Column(name = "name", length = 45)
    val name: String,

    @Column(name = "depth", nullable = false)
    val depth: Int,

    @Column(name = "sort", nullable = false)
    val sort: Int,

    @Column(name = "display_state", nullable = false)
    val displayState: Boolean,

    @Column(name = "ref")
    val ref: String? = null

) : DateColumnEntity() {


}

