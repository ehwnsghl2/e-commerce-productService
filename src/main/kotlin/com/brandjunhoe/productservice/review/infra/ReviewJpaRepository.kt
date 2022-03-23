package com.brandjunhoe.productservice.review.infra

import com.brandjunhoe.productservice.product.domain.Product
import com.brandjunhoe.productservice.product.domain.ProductCode
import com.brandjunhoe.productservice.product.domain.ProductRepository
import com.brandjunhoe.productservice.review.domain.Review
import com.brandjunhoe.productservice.review.domain.ReviewRepository
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ReviewJpaRepository : JpaRepository<Review, UUID>, ReviewRepository {
}