package com.brandjunhoe.productservice.common.page

data class TotalPageDTO(
    private val number: Int,
    val totalPages: Int,
    val totalElements: Long
) {

    fun getNumber(): Int =
        number + 1

}