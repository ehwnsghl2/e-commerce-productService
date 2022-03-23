package com.brandjunhoe.productservice.main.presentation

import com.brandjunhoe.productservice.category.application.CategoryService
import com.brandjunhoe.productservice.category.application.dto.CategoryDTO
import com.brandjunhoe.productservice.common.response.CommonResponse
import com.brandjunhoe.productservice.main.presentation.dto.ResMainDTO
import com.brandjunhoe.productservice.product.application.ProductService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Create by DJH on 2022/03/22.
 */
@RestController
@RequestMapping("/main")
class MainController(
    val productService: ProductService,
    val categoryService: CategoryService
) {


    @GetMapping
    fun main(): CommonResponse<ResMainDTO> {

        val categorys = categoryService.findAll()
        val top8Products = productService.findTop8Products()

        return CommonResponse(ResMainDTO(categorys, top8Products))
    }


}