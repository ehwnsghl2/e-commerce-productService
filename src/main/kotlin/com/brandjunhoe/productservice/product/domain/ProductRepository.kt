package com.brandjunhoe.productservice.product.domain

/**
 * Create by DJH on 2022/03/21.
 */
interface ProductRepository {
    fun findByProductCode(productCode: ProductCode): Product?
    fun findTop8ByDisplayStateIsTrueOrderByTotalSaleCountDesc(): List<Product>
}