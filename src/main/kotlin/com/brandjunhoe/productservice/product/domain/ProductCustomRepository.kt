package com.brandjunhoe.productservice.product.domain

import com.brandjunhoe.productservice.product.application.dto.ProductSearchDTO

/**
 * Create by DJH on 2022/03/21.
 */
interface ProductCustomRepository {
    fun findByCategoryCodesIn(categoryCodes: List<String>): List<ProductSearchDTO>
}