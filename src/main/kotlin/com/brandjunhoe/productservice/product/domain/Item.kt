package com.brandjunhoe.productservice.product.domain

import com.brandjunhoe.productservice.common.domain.DateColumnEntity
import com.brandjunhoe.productservice.product.domain.enums.StockQuantityCheckEnum
import org.hibernate.annotations.Where
import javax.persistence.*

@Entity
@Table(name = "item")
@Where(clause = "deldate IS NOT NULL")
class Item(

    @EmbeddedId
    val itemCode: ItemCode,

    @Column(name = "name", length = 255)
    val name: String,

    @Column(name = "value", length = 255)
    val value: String,

    @Column(name = "quantity")
    val quantity: Int,

    @Column(name = "safety_stock_quantity")
    val safetyStockQuantity: Int,

    @Enumerated(EnumType.STRING)
    @Column(name = "stock_quantity_check", columnDefinition = "enum", nullable = false)
    val stockQuantityCheckEnum: StockQuantityCheckEnum,

    @Column(name = "display_state", nullable = false)
    val displayState: Boolean,

    @Column(name = "selling_state", nullable = false)
    val sellingState: Boolean,

    @Column(name = "add_price")
    val addPrice: Int = 0,

    @Column(name = "total_sale_quantity")
    val totalSaleQuantity: Int = 0

) : DateColumnEntity() {


}

