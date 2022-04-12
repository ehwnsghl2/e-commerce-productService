package com.brandjunhoe.productservice.product.application

import com.brandjunhoe.productservice.product.application.dto.ProductInternalDTO
import com.brandjunhoe.productservice.product.domain.ProductCode
import com.brandjunhoe.productservice.product.domain.ProductRepository
import org.springframework.stereotype.Service

/**
 * Create by DJH on 2022/03/21.
 */
@Service
class ProductInternalService(private val productRepository: ProductRepository) {

    fun findAllByProductcodes(productCodes: List<String>): List<ProductInternalDTO> {
        val products = productRepository.findByProductCodeIn(productCodes.map { ProductCode(it) })
        return products.map { ProductInternalDTO(it) }
    }

}