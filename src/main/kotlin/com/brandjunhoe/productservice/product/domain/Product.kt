package com.brandjunhoe.productservice.product.domain

import com.brandjunhoe.productservice.category.domain.Category
import com.brandjunhoe.productservice.category.domain.CategoryCode
import com.brandjunhoe.productservice.common.domain.DateColumnEntity
import com.brandjunhoe.productservice.product.domain.enums.ProductGradeLimitEnum
import com.brandjunhoe.productservice.product.domain.enums.ProductTypeEnum
import org.hibernate.annotations.BatchSize
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.Where
import javax.persistence.*

@Entity
@Table(name = "product")
//@Where(clause = "deldate IS NULL")
class Product(

    @EmbeddedId
    val productCode: ProductCode,

    /*@BatchSize(size = 100)
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
    @JoinTable(
        name = "product_category",
        joinColumns = [JoinColumn(name = "product_code", nullable = false,)],
        inverseJoinColumns = [JoinColumn(name = "category_code", nullable = false)]
    ) */
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
        name = "product_category",
        joinColumns = [JoinColumn(name = "product_code", nullable = false)],
    )
    val category: Set<CategoryCode>,

    @Column(name = "name", length = 100)
    val name: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "enum", nullable = false)
    @ColumnDefault("PRODUCT")
    val type: ProductTypeEnum = ProductTypeEnum.PRODUCT,
    //@Convert(converter = ProductTypeConverter::class)
    //val type: String,

    @Column(name = "summary", nullable = false)
    val summary: String,

    @Column(name = "description", nullable = false)
    val description: String,

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

    @Column(name = "discount_rate")
    val discountRate: Int? = null,

    @Column(name = "discount_price")
    val discountPrice: Int? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "grade_limit", columnDefinition = "enum", nullable = false)
    @ColumnDefault("ALL")
    val gradeLimit: ProductGradeLimitEnum = ProductGradeLimitEnum.ALL,

    @Column(name = "sold_out_state", nullable = false)
    val soldOutState: Boolean = false,

    @Column(name = "grade_sale_state", nullable = false)
    val gradeSaleState: Boolean = true,

    @Column(name = "memo")
    val memo: String? = null,

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "product", cascade = [CascadeType.PERSIST], optional = false)
    //@JoinColumn(name = "product_code", nullable = false)
    val mainImage: ProductMainImage,

    /* @ElementCollection
     @CollectionTable(name = "item", joinColumns = [JoinColumn(name = "product_code")])
     //@OrderColumn(name = "line_idx")
     val items: List<Item> = listOf()*/

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
    @JoinColumn(name = "product_code", nullable = false)
    @Where(clause = "display_state = true")
    val items: List<Item> = listOf()


) : DateColumnEntity() {

    @Version
    var version: Long? = null

    @Column(name = "review_count", nullable = false)
    var reviewCount: Int? = null
        protected set

    @Column(name = "review_rating", nullable = false)
    var reviewRating: Double? = null
        protected set

    @Column(name = "total_sale_count")
    var totalSaleCount: Int? = null
        protected set


    fun updateReviewSummary(reviewCount: Int, reviewRating: Double) {
        this.reviewCount = reviewCount
        this.reviewRating = reviewRating
    }

    fun updateItemStockQuantity(itemCode: String, quantity: Int) {
        items.find { it.itemCode == ItemCode(itemCode) }
            ?.also { item -> item.updateStockQuantity(quantity) }
    }

}