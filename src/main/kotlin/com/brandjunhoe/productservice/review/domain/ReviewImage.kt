package com.brandjunhoe.productservice.review.domain

import javax.persistence.Column
import javax.persistence.Embeddable

/**
 * Create by DJH on 2022/03/18.
 */
@Embeddable
class ReviewImage(path: String) {

    @Column(name = "path")
    private val path: String = path

}