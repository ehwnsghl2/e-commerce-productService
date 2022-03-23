package com.brandjunhoe.productservice.product.presentation

import com.brandjunhoe.productservice.common.response.CommonResponse
import com.brandjunhoe.productservice.product.application.ProductService
import com.brandjunhoe.productservice.product.domain.ProductCode
import com.brandjunhoe.productservice.product.presentation.dto.ResProductDetailDTO
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 * Create by DJH on 2022/03/21.
 */
@RestController
@RequestMapping("/product")
class ProductController(val productService: ProductService) {


    @GetMapping
    fun get(): String =
        "/"


    @GetMapping("/{productCode}")
    fun detail(
        @PathVariable(value = "productCode") @Valid productCode: ProductCode
    ): CommonResponse<ResProductDetailDTO> = CommonResponse(productService.detail(productCode))

}