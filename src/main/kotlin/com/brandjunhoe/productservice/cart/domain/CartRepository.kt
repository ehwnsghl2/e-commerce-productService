package com.brandjunhoe.productservice.cart.domain

import java.util.*

interface CartRepository {
    fun findById(id: UUID): Cart?
    fun findByUsrIdAndProductCodeAndItemCode(usrId: UUID, productCode: String, itemCode: String): Cart?
    fun save(card: Cart): Cart
    fun findByUsrId(usrId: UUID): List<Cart>
}