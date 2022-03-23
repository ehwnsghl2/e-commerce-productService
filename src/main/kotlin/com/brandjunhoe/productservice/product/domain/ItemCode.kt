package com.brandjunhoe.productservice.product.domain

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

/**
 * Create by DJH on 2022/03/18.
 */
@Embeddable
class ItemCode(itemCode: String) : Serializable {

    @Column(name = "item_code")
    private val itemCode: String = itemCode


}