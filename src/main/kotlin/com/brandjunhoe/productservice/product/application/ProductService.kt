package com.brandjunhoe.productservice.product.application

import com.brandjunhoe.productservice.common.exception.DataNotFoundException
import com.brandjunhoe.productservice.product.application.dto.ItemOptionValueDTO
import com.brandjunhoe.productservice.product.application.dto.ItemOptionsDTO
import com.brandjunhoe.productservice.product.application.dto.ProductDTO
import com.brandjunhoe.productservice.product.domain.ItemRepository
import com.brandjunhoe.productservice.product.domain.ProductCode
import com.brandjunhoe.productservice.product.domain.ProductRepository
import com.brandjunhoe.productservice.product.presentation.dto.ResProductDetailDTO
import org.springframework.stereotype.Service

/**
 * Create by DJH on 2022/03/21.
 */
@Service
class ProductService(val productRepository: ProductRepository, val itemRepository: ItemRepository) {

    //findTop10ByOrderByRentCntDesc
    fun findTop8Products(): List<ProductDTO> {
        return productRepository.findTop8ByDisplayStateIsTrueOrderByTotalSaleCountDesc().map {
            ProductDTO(it.productCode.productCode, it.name, it.sellingPrice, it.sellingState, it.mainImage.path)
        }

    }

    fun detail(productCode: ProductCode): ResProductDetailDTO {
        println("productCode : $productCode")
        val product =
            productRepository.findByProductCode(
                productCode
            ) ?: throw DataNotFoundException("product data not found")


        val item = itemRepository.findByProductCode(productCode)

        val items = item.groupBy { it.name }


        val options = items.map { (name, value) ->
            ItemOptionsDTO(
                name,
                value.map { ItemOptionValueDTO(it.value, it.quantity, it.addPrice, it.sellingState) })
        }


        return ResProductDetailDTO(product.name, product.mainImage.path ?: "", options)

    }

}