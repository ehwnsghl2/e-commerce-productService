package com.brandjunhoe.productservice.product.domain

import com.brandjunhoe.productservice.category.domain.Category
import com.brandjunhoe.productservice.category.domain.CategoryCode
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph

/**
 * Create by DJH on 2022/03/21.
 */
interface ProductRepository {
    fun findByProductCode(productCode: ProductCode): Product?
    fun findByProductCodeIn(productCode: List<ProductCode>): List<Product>
    fun findTop8ByDisplayStateIsTrueOrderByTotalSaleCountDesc(): List<Product>
    fun findAllByNameContaining(pageable: Pageable, name: String): Page<Product>
    fun findByCategoryIn(category: List<Category>): List<Product>
    @EntityGraph(attributePaths = ["category"], type = EntityGraph.EntityGraphType.LOAD)
    fun findByCategoryCategoryCodeIn(categoryCodes: List<CategoryCode>): List<Product>
}