package com.brandjunhoe.productservice.category.domain

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

/**
 * Create by DJH on 2022/03/18.
 */
@Embeddable
data class CategoryCode(

    @Column(name = "category_code")
    val categoryCode: String

) : Serializable {



}