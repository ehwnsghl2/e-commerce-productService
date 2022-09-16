package com.brandjunhoe.productservice.wish.infra

import com.brandjunhoe.productservice.wish.domain.Wish
import com.brandjunhoe.productservice.wish.domain.WishRepository
import org.springframework.data.jpa.repository.JpaRepository

interface WishJpaRepository : JpaRepository<Wish, Long>, WishRepository {
}