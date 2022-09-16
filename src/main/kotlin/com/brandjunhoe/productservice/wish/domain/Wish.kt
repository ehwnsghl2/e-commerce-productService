package com.brandjunhoe.productservice.wish.domain

import com.brandjunhoe.productservice.common.domain.DateColumnEntity
import com.brandjunhoe.productservice.product.domain.ProductCode
import org.hibernate.annotations.GenericGenerator
import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "wish")
@IdClass(WishId::class)
class Wish(

    @Column(name = "usr_id", nullable = false)
    val usrId: UUID,

    @EmbeddedId
    val productCode: ProductCode,

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    val id: UUID? = null

) : DateColumnEntity(), Serializable {

    @Version
    var version: Long? = null

}

