package com.brandjunhoe.productservice.product.domain

import com.brandjunhoe.productservice.category.domain.CategoryCode

/**
 * Create by DJH on 2022/03/21.
 */
interface ProductRepository {
    fun findByProductCode(productCode: ProductCode): Product?
    fun findTop8ByDisplayStateIsTrueOrderByTotalSaleCountDesc(): List<Product>
    //fun findByCategoryCodesIn(categoryCodes: Set<CategoryCode>): List<Product>
    //fun findByCategoryCodesCategoryCodeIn(categoryCodes: List<String>): List<Product>
}