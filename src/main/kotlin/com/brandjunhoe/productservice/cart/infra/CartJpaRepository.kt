package com.brandjunhoe.productservice.cart.infra

import com.brandjunhoe.productservice.cart.domain.Cart
import com.brandjunhoe.productservice.cart.domain.CartRepository
import org.springframework.data.jpa.repository.JpaRepository

interface CartJpaRepository : JpaRepository<Cart, Long>, CartRepository {
}