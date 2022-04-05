package com.brandjunhoe.productservice.product.application

import com.brandjunhoe.productservice.category.domain.CategoryCode
import com.brandjunhoe.productservice.common.exception.BadRequestException
import com.brandjunhoe.productservice.common.exception.DataNotFoundException
import com.brandjunhoe.productservice.product.application.dto.ItemOptionValueDTO
import com.brandjunhoe.productservice.product.application.dto.ItemOptionsDTO
import com.brandjunhoe.productservice.product.application.dto.ProductDTO
import com.brandjunhoe.productservice.product.application.dto.ProductSearchDTO
import com.brandjunhoe.productservice.product.domain.ItemRepository
import com.brandjunhoe.productservice.product.domain.ProductCode
import com.brandjunhoe.productservice.product.domain.ProductCustomRepository
import com.brandjunhoe.productservice.product.domain.ProductRepository
import com.brandjunhoe.productservice.product.presentation.dto.ResProductDetailDTO
import com.brandjunhoe.productservice.review.domain.event.ProductReviewUpdateEvent
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

/**
 * Create by DJH on 2022/03/21.
 */
@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val productCustomRepository: ProductCustomRepository,
    private val itemRepository: ItemRepository
) {

    //findTop10ByOrderByRentCntDesc
    fun findTop8Products(): List<ProductDTO> {
        return productRepository.findTop8ByDisplayStateIsTrueOrderByTotalSaleCountDesc().map {
            ProductDTO(it.productCode.productCode, it.name, it.sellingPrice, it.sellingState, it.mainImage.path)
        }

    }

    fun detail(productCode: String): ResProductDetailDTO {
        println("productCode : $productCode")
        val product =
            productRepository.findByProductCode(
                ProductCode(productCode)
            ) ?: throw DataNotFoundException("product data not found")


        val items = product.items

        val itemGroup = items.groupBy { it.name }

        val options = itemGroup.map { (name, value) ->
            ItemOptionsDTO(
                name,
                value.map { ItemOptionValueDTO(it.value, it.quantity, it.addPrice, it.sellingState) })
        }

        return ResProductDetailDTO(product.name, product.mainImage.path ?: "", options)

    }

    fun findCategoryProducts(categoryCodes: List<String>): List<ProductSearchDTO> {
        return productCustomRepository.findByCategoryCodesIn(categoryCodes)

        /*   productRepository.findByCategoryCodesCategoryCodeIn(categoryCodes)
               .map {
                   println(it.productCode.productCode)
                   ProductSearchDTO(it) }
   */
    }

    @EventListener
    @Async
    fun updateReviewSummary(event: ProductReviewUpdateEvent) {
        val product = productRepository.findByProductCode(event.productCode)
            ?: throw BadRequestException("product not found")

        product.updateReviewSummary(event.reviewCount, event.reviewRating)

    }

}