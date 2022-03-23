package com.brandjunhoe.productservice.category.application

import com.brandjunhoe.productservice.category.application.dto.CategoryDTO
import com.brandjunhoe.productservice.category.domain.Category
import com.brandjunhoe.productservice.category.domain.CategoryRepository
import org.springframework.stereotype.Service

/**
 * Create by DJH on 2022/03/22.
 */
@Service
class CategoryService(val categoryRepository: CategoryRepository) {


    fun findAll(): List<CategoryDTO> {

        val categorys = categoryRepository.findAllByDisplayStateIsTrueOrderBySortAsc()


        return categorys.filter { it.depth == 1 }
            .map { it ->
                CategoryDTO(
                    categoryCode = it.categoryCode.categoryCode,
                    name = it.name,
                    refCategorys = getRefCategories(it.categoryCode.categoryCode, categorys.groupBy { it.ref })
                )
            }

    }


    fun getRefCategories(categoryCode: String, refCategorys: Map<String?, List<Category>>): List<CategoryDTO>? =
        refCategorys[categoryCode]?.let {
            it.map { category ->
                CategoryDTO(
                    category.categoryCode.categoryCode,
                    category.name,
                    getRefCategories(category.categoryCode.categoryCode, refCategorys)
                )
            }
        } ?: run { null }


}