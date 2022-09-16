package com.brandjunhoe.productservice.wish.domain

import java.util.*

interface WishRepository {
    fun findById(id: UUID): Wish?
    fun findByUsrIdAndProductCode(usrId: UUID, productCode: String): Wish?
    fun save(wish: Wish) : Wish
    fun deleteById(id: UUID)
    fun findByUsrId(usrId: UUID): List<Wish>
}