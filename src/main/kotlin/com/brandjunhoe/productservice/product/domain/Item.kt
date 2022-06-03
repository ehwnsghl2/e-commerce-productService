package com.brandjunhoe.productservice.product.domain

import com.brandjunhoe.productservice.common.domain.DateColumnEntity
import com.brandjunhoe.productservice.product.domain.converter.ItemConverter
import com.brandjunhoe.productservice.product.domain.enums.StockQuantityCheckEnum
import org.hibernate.annotations.Where
import javax.persistence.*

@Entity
@Table(name = "item")
@Where(clause = "deldate IS NULL")
class Item(

    @EmbeddedId
    val itemCode: ItemCode,

    @ManyToOne(targetEntity = Product::class, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_code")
    val product: Product,

    @Column(name = "name", length = 255, nullable = false)
    val name: String,

    @Column(name = "value", columnDefinition = "json",/* length = 255,*/ nullable = false)
    @Convert(converter = ItemConverter::class)
    val value: List<Map<Any,String>>,

    @Column(name = "quantity", nullable = false)
    val quantity: Int,

    @Column(name = "safety_stock_quantity", nullable = false)
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

    fun changeQuantityMinus(quantity: Int) {
        this.quantity.minus(quantity)
    }

    fun changeQuantityPlus(quantity: Int) {
        this.quantity.plus(quantity)
    }

}

