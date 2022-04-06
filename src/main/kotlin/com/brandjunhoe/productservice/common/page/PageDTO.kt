package com.brandjunhoe.productservice.common.page

data class PageDTO<T>(private val total: TotalPageDTO, private val data: T)