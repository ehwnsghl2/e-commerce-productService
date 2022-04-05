package com.brandjunhoe.productservice.config

import com.brandjunhoe.productservice.review.domain.enums.ReviewTypeEnum
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding


@ConstructorBinding
@ConfigurationProperties(prefix = "review.save-rate")
class ReviewSaveProperties(
    private val text: Int,
    private val photo: Int
) {

    fun rate(reviewType: ReviewTypeEnum): Int {
        return if (reviewType == ReviewTypeEnum.PHOTO)
            photo
        else text
    }

}