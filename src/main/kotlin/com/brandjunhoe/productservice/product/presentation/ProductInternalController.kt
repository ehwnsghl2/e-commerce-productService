package com.brandjunhoe.productservice.product.presentation

import com.brandjunhoe.productservice.common.response.CommonResponse
import com.brandjunhoe.productservice.product.application.ProductInternalService
import com.brandjunhoe.productservice.product.application.dto.ProductInternalDTO
import com.brandjunhoe.productservice.product.presentation.dto.ResProductDetailDTO
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.NotEmpty

/**
 * Create by DJH on 2022/04/06.
 */
@RestController
@RequestMapping("/product-internal")
class ProductInternalController(val productInternalService: ProductInternalService) {


    @GetMapping
    fun findProductsByProductcodes(
        @RequestParam("productCodes") @Valid @NotEmpty productCodes: List<String>
    ): CommonResponse<List<ProductInternalDTO>> = CommonResponse(productInternalService.findAllByProductcodes(productCodes))


}