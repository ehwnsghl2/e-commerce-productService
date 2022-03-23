package com.brandjunhoe.productservice.category.domain

/**
 * Create by DJH on 2022/03/21.
 */
interface CategoryRepository {
    fun findAllByDisplayStateIsTrueOrderBySortAsc(): List<Category>

}