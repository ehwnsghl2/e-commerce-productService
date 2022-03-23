package com.brandjunhoe.productservice.main.presentation.dto

import com.brandjunhoe.productservice.category.application.dto.CategoryDTO
import com.brandjunhoe.productservice.product.application.dto.ProductDTO

/**
 * Create by DJH on 2022/03/21.
 */
class ResMainDTO(val categorys: List<CategoryDTO>, val products: List<ProductDTO>)