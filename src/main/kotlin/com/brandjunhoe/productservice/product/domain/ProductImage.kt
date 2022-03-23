package com.brandjunhoe.productservice.product.domain

import com.brandjunhoe.productservice.common.domain.DateColumnEntity
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

/**
 * Create by DJH on 2022/03/22.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", columnDefinition = "enum")
@Table(name = "product_image")
abstract class ProductImage(

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    val id: UUID = UUID.randomUUID(),


    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "product_code", nullable = false)
    val product: Product? = null,

    @Column(name = "sort", nullable = false)
    val sort: Int? = null,

    @Column(name = "path", nullable = false)
    val path: String? = null

) : DateColumnEntity() {

    // constructor() : this(UUID.randomUUID(), sort = 0, path = "")

}