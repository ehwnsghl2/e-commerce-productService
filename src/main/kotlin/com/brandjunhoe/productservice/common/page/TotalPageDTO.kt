package com.brandjunhoe.productservice.common.page

data class TotalPageDTO(
    private val number: Int,
    private val totalPages: Int,
    private val totalElements: Long
) {

    fun getNumber(): Int =
        number + 1

}