package com.brandjunhoe.productservice.product.domain

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

/**
 * Create by DJH on 2022/03/21.
 */
interface ProductRepository {
    fun findByProductCode(productCode: ProductCode): Product?
    fun findByProductCodeIn(productCode: List<ProductCode>): List<Product>
    fun findTop8ByDisplayStateIsTrueOrderByTotalSaleCountDesc(): List<Product>
    fun findAllByNameContaining(pageable: Pageable, name: String): Page<Product>
    //fun findByCategoryCodesIn(categoryCodes: Set<CategoryCode>): List<Product>
    //fun findByCategoryCodesCategoryCodeIn(categoryCodes: List<String>): List<Product>
}