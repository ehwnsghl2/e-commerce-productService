package com.brandjunhoe.productservice.category.infra

import com.brandjunhoe.productservice.category.domain.Category
import com.brandjunhoe.productservice.category.domain.CategoryCode
import com.brandjunhoe.productservice.category.domain.CategoryRepository
import com.brandjunhoe.productservice.product.domain.Item
import com.brandjunhoe.productservice.product.domain.ItemCode
import com.brandjunhoe.productservice.product.domain.ItemRepository
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryJpaRepository : JpaRepository<Category, CategoryCode>, CategoryRepository {

}