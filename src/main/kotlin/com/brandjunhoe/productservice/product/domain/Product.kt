package com.brandjunhoe.productservice.product.domain

import com.brandjunhoe.productservice.common.domain.DateColumnEntity
import com.brandjunhoe.productservice.product.domain.enums.ProductGradeLimitEnum
import com.brandjunhoe.productservice.product.domain.enums.ProductTypeEnum
import org.hibernate.annotations.ColumnDefault
import javax.persistence.*

@Entity
@Table(name = "product")
//@Where(clause = "deldate IS NOT NULL")
class Product(

    @EmbeddedId
    val productCode: ProductCode,

    @Column(name = "name", length = 100)
    val name: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "enum", nullable = false)
    @ColumnDefault("PRODUCT")
    val type: ProductTypeEnum = ProductTypeEnum.PRODUCT,

    @Column(name = "summary_description", nullable = false)
    val summaryDescription: String,

    @Column(name = "detail_description", nullable = false)
    val detailDescription: String,

    @Column(name = "supplier_price")
    val supplierPrice: Int = 0,

    @Column(name = "retail_price", nullable = false)
    val retailPrice: Int,

    @Column(name = "selling_price", nullable = false)
    val sellingPrice: Int,

    @Column(name = "display_state", nullable = false)
    val displayState: Boolean,

    @Column(name = "selling_state", nullable = false)
    val sellingState: Boolean,

    @Enumerated(EnumType.STRING)
    @Column(name = "grade_limit", columnDefinition = "enum", nullable = false)
    @ColumnDefault("ALL")
    val gradeLimit: ProductGradeLimitEnum = ProductGradeLimitEnum.ALL,

    @Column(name = "sold_out_state", nullable = false)
    val soldOutState: Boolean = false,

    @Column(name = "grade_sale_state", nullable = false)
    val gradeSaleState: Boolean = true,

    @Column(name = "total_sale_count", nullable = false)
    val totalSaleCount: Int = 0,

    @Column(name = "memo")
    val memo: String? = null,

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "product", cascade = [CascadeType.PERSIST])
    val mainImage: ProductMainImage

    /*@ElementCollection
    @CollectionTable(name = "item", joinColumns = [JoinColumn(name = "product_code")])
    //@OrderColumn(name = "line_idx")
    val items: List<Item> = listOf()*/

    /*@OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = [CascadeType.PERSIST])
    @Where(clause = "display_state = true")
    val items: List<Item> = listOf()*/


) : DateColumnEntity() {


}

