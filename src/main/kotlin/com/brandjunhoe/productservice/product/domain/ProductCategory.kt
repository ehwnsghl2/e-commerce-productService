package com.brandjunhoe.productservice.product.domain

import java.io.Serializable
import javax.persistence.*

/**
 * Create by DJH on 2022/03/25.
 */
@Entity
@Table(name = "product_category")
class ProductCategory(

    @Id @Column(name = "product_code")
    val productCode: String,

    @Id @Column(name = "category_code")
    val categoryCode: String

) : Serializable