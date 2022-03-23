package com.brandjunhoe.productservice.product.infra

import com.brandjunhoe.productservice.product.domain.Item
import com.brandjunhoe.productservice.product.domain.ItemCode
import com.brandjunhoe.productservice.product.domain.ItemRepository
import org.springframework.data.jpa.repository.JpaRepository

interface ItemJpaRepository : JpaRepository<Item, ItemCode>, ItemRepository {
}