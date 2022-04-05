package com.brandjunhoe.productservice.product.presentation

import com.brandjunhoe.productservice.category.application.CategoryService
import com.brandjunhoe.productservice.common.response.CommonResponse
import com.brandjunhoe.productservice.product.application.ProductService
import com.brandjunhoe.productservice.product.application.dto.ProductSearchDTO
import com.brandjunhoe.productservice.product.presentation.dto.ResProductDetailDTO
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.NotBlank

/**
 * Create by DJH on 2022/03/21.
 */
@RestController
@RequestMapping("/product")
class ProductController(
    val productService: ProductService,
    val categoryService: CategoryService
) {


    @GetMapping("/{productCode}")
    fun detail(
        @PathVariable @Valid @NotBlank productCode: String
    ): CommonResponse<ResProductDetailDTO> = CommonResponse(productService.detail(productCode))


    @GetMapping("/category")
    fun findCategoryProducts(@RequestParam(value = "categoryCode") @Valid @NotBlank categoryCode: String)
    : CommonResponse<List<ProductSearchDTO>> {

        val categorys = categoryService.findRefCategorys(categoryCode).map { it.categoryCode.categoryCode }

        return CommonResponse(productService.findCategoryProducts(categorys))
    }

}