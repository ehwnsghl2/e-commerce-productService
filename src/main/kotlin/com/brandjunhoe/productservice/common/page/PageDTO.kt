package com.brandjunhoe.productservice.common.page

data class PageDTO<T>(val total: TotalPageDTO, val data: T)