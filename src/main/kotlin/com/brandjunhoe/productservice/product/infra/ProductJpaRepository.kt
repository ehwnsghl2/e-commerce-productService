package com.brandjunhoe.productservice.product.infra

import com.brandjunhoe.productservice.product.domain.Product
import com.brandjunhoe.productservice.product.domain.ProductCode
import com.brandjunhoe.productservice.product.domain.ProductRepository
import org.springframework.data.jpa.repository.JpaRepository

interface ProductJpaRepository : JpaRepository<Product, String>, ProductRepository {
}