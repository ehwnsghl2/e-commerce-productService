package com.brandjunhoe.productservice.cart.domain

import com.brandjunhoe.productservice.common.domain.DateDeleteColumnEntity
import com.brandjunhoe.productservice.product.domain.ProductCode
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Where
import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "cart")
@Where(clause = "deldate IS NULL")
@IdClass(CartId::class)
class Cart(

    @Column(name = "usr_id", nullable = false)
    val usrId: UUID,

    @EmbeddedId
    val productCode: ProductCode,

    @Column(name = "item_code", length = 255, nullable = false)
    val itemCode: String,

    quantity: Int,

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    val id: UUID? = null

) : DateDeleteColumnEntity(), Serializable {

    @Version
    var version: Long? = null

    @Column(name = "quantity", nullable = false)
    var quantity: Int = quantity
        protected set

    fun changeQuantity(quantity: Int) {
        this.quantity = quantity
    }

    fun addQuantity(quantity: Int) {
        this.quantity = quantity.plus(quantity)
    }

}

