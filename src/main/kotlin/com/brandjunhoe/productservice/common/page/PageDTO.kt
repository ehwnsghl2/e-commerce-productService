package com.brandjunhoe.productservice.common.page

data class PageDTO<T>(val total: TotalPageDTO, val data: T) {
    //constructor(page: Page<T>) : this(TotalPageDTO(page.number, page.totalPages, page.totalElements), page.content)
    //constructor(page: Page<T>, data: List<T>) : this(TotalPageDTO(page.number, page.totalPages, page.totalElements), data)
}