package com.brandjunhoe.productservice.product.presentation.dto

import com.brandjunhoe.productservice.product.application.dto.ItemOptionsDTO

/**
 * Create by DJH on 2022/03/21.
 */
class ResProductDetailDTO(val productName: String, val productImage: String, val options: List<ItemOptionsDTO>)