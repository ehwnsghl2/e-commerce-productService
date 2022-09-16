package com.brandjunhoe.productservice.wish.domain

import com.brandjunhoe.productservice.product.domain.ProductCode
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.EmbeddedId

/**
 * Create by DJH on 2022/09/16.
 */
class WishId (

    @Column(name = "id")
    private val id: Long,

    @EmbeddedId
    private val productCode: ProductCode

) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as WishId

        if (id != other.id) return false
        if (productCode != other.productCode) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + productCode.hashCode()
        return result
    }
}