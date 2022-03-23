package com.brandjunhoe.productservice.product.domain

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

/**
 * Create by DJH on 2022/03/18.
 */
@Embeddable
class ProductCode(

    @Column(name = "product_code")
    val productCode: String

) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ProductCode

        if (productCode != other.productCode) return false

        return true
    }

    override fun hashCode(): Int {
        return productCode.hashCode()
    }
}