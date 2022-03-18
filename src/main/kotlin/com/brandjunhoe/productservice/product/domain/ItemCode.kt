package com.brandjunhoe.productservice.product.domain

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

/**
 * Create by DJH on 2022/03/18.
 */
@Embeddable
class ItemCode(itemCode: String) : Serializable {

    @Column(name = "itemCode")
    private val itemCode: String = itemCode


}