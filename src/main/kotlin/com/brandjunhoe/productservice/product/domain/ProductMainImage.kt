package com.brandjunhoe.productservice.product.domain

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

/**
 * Create by DJH on 2022/03/23.
 */
@Entity
@DiscriminatorValue("MAIN")
class ProductMainImage : ProductImage()