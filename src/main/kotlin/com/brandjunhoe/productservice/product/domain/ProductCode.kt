package com.brandjunhoe.productservice.product.domain

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

/**
 * Create by DJH on 2022/03/18.
 */
@Embeddable
class ProductCode(productCode: String) : Serializable {

    @Column(name = "product_code")
    private val productCode: String = productCode


}