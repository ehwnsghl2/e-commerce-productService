package com.brandjunhoe.productservice.product.infra.impl

import com.brandjunhoe.productservice.product.application.dto.ProductSearchDTO
import com.brandjunhoe.productservice.product.domain.ProductCustomRepository
import com.brandjunhoe.productservice.product.domain.QProduct.product
import com.brandjunhoe.productservice.product.domain.QProductCategory.productCategory
import com.brandjunhoe.productservice.product.domain.QProductMainImage.productMainImage
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

/**
 * Created by DJH to 2022/03/25
 */
@Repository
class ProductCustomRepositoryImpl(private val queryFactory: JPAQueryFactory) : ProductCustomRepository {

    override fun findByCategoryCodesIn(categoryCodes: List<String>): List<ProductSearchDTO> {

        return queryFactory.select(
            Projections.constructor(
                ProductSearchDTO::class.java,
                product.productCode.productCode,
                productMainImage.path,
                product.name,
                product.discountRate,
                product.discountPrice,
                product.sellingPrice,
                product.sellingState,
                product.soldOutState,
                product.reviewCount,
                product.reviewRating
            )

        )
            .from(product)
            .join(product.mainImage, productMainImage)
            .join(productCategory).on(
                product.productCode.productCode
                    .eq(productCategory.productCode)
            ).where(productCategory.categoryCode.`in`(categoryCodes))
            .fetch()


    }


}