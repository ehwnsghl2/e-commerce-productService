package com.brandjunhoe.productservice.product.application

import com.brandjunhoe.productservice.common.page.PageDTO
import com.brandjunhoe.productservice.common.page.TotalPageDTO
import com.brandjunhoe.productservice.consumer.dto.ProductItemQuantityMinusUpdateDTO
import com.brandjunhoe.productservice.product.application.dto.ItemOptionValueDTO
import com.brandjunhoe.productservice.product.application.dto.ItemOptionsDTO
import com.brandjunhoe.productservice.product.application.dto.ProductDTO
import com.brandjunhoe.productservice.product.application.dto.ProductSearchDTO
import com.brandjunhoe.productservice.product.application.exception.ProductNotFoundException
import com.brandjunhoe.productservice.product.domain.ItemRepository
import com.brandjunhoe.productservice.product.domain.ProductCode
import com.brandjunhoe.productservice.product.domain.ProductCustomRepository
import com.brandjunhoe.productservice.product.domain.ProductRepository
import com.brandjunhoe.productservice.product.presentation.dto.ResProductDetailDTO
import com.brandjunhoe.productservice.review.domain.event.ProductReviewUpdateEvent
import org.springframework.context.event.EventListener
import org.springframework.data.domain.PageRequest
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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

        val product = findByProductCode(productCode)

        val items = product.items

        /*val itemGroup = items.groupBy { it.value }

        val options = itemGroup.map { (name, value) ->
            ItemOptionsDTO(
                name,
                value.map { ItemOptionValueDTO(it.value, it.quantity, it.addPrice, it.sellingState) })
        }*/

        items.forEach {
            println(it)
        }


        val itemGroups = items.groupBy { it.name }


        val options = itemGroups.map { itemGroup ->
            val values = itemGroup.value.map { item ->
                val value = item.value.map { it.values }.joinToString(" ")
                ItemOptionValueDTO(value, item.quantity, item.addPrice, item.sellingState)
            }
            ItemOptionsDTO(itemGroup.key, values)
        }




        return ResProductDetailDTO(product.name, product.mainImage.path ?: "", product.type.name, options)

    }

    fun findCategoryProducts(categoryCodes: List<String>): List<ProductSearchDTO> {
        return productCustomRepository.findByCategoryCodesIn(categoryCodes)

        /*return productRepository.findByCategoryCodesIn(categoryCodes.toSet())
            .map {
                println(it.productCode.productCode)
                ProductSearchDTO(it)
            }*/
    }

    fun findAllByName(name: String, pageRequest: PageRequest): PageDTO<List<ProductSearchDTO>> {
        val products = productRepository.findAllByNameContaining(pageRequest, name)
        return PageDTO(
            TotalPageDTO(products.number, products.totalPages, products.totalElements),
            products.content.map { ProductSearchDTO(it) }
        )
    }

    fun updateProductItemStockMinus(request: ProductItemQuantityMinusUpdateDTO) {
        val product = findByProductCode(request.productCode)
        product.updateItemQuantityMinus(request.itemCode, request.quantity)
    }

    @EventListener
    @Async
    @Transactional
    fun updateReviewSummary(event: ProductReviewUpdateEvent) {
        val product = productRepository.findByProductCode(event.productCode)
            ?: throw ProductNotFoundException()

        product.updateReviewSummary(event.reviewCount, event.reviewRating)

    }

    private fun findByProductCode(productCode: String) =
        productRepository.findByProductCode(ProductCode(productCode))
            ?: throw ProductNotFoundException()

}