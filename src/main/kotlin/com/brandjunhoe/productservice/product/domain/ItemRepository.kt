package com.brandjunhoe.productservice.product.domain

/**
 * Create by DJH on 2022/03/21.
 */
interface ItemRepository {
    fun findByProductCode(productCode: ProductCode): List<Item>
}