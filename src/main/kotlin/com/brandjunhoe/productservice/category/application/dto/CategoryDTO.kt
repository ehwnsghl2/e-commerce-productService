package com.brandjunhoe.productservice.category.application.dto

import com.brandjunhoe.productservice.category.domain.CategoryCode

/**
 * Create by DJH on 2022/03/22.
 */
class CategoryDTO(

    val categoryCode: String,

    val name: String,

    val refCategorys: List<CategoryDTO>? = null

)