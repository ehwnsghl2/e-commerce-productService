package com.brandjunhoe.productservice.review.presentation.dto

import com.brandjunhoe.productservice.common.exception.BadRequestException
import com.brandjunhoe.productservice.product.domain.ItemCode
import com.brandjunhoe.productservice.product.domain.ProductCode
import com.brandjunhoe.productservice.review.application.exception.ReviewPhotoIntputValueException
import com.brandjunhoe.productservice.review.domain.Review
import com.brandjunhoe.productservice.review.domain.ReviewImage
import com.brandjunhoe.productservice.review.domain.enums.ReviewTypeEnum
import org.springframework.web.multipart.MultipartFile
import java.util.*
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 * Create by DJH on 2022/03/28.
 */
class ReqReviewSaveDTO(

    @field:NotBlank
    val usrId: UUID,

    @field:NotBlank
    val orderProductCode: String,

    @field:NotBlank
    val productCode: String,

    @field:NotBlank
    val itemCode: String,

    @field:NotNull
    val type: ReviewTypeEnum,

    @field:NotNull
    @field:Min(value = 1)
    @field:Max(value = 5)
    val score: Int,

    @field:NotBlank
    val contents: String,

    val images: List<MultipartFile>? = null

) {

    fun validate() {
        if (type == ReviewTypeEnum.PHOTO && images == null)
            throw ReviewPhotoIntputValueException()
    }

    fun toEntity(mileage: Int): Review =
        Review(
            usrId,
            orderProductCode,
            productCode,
            itemCode,
            type,
            score,
            contents,
            mileage
        )

}