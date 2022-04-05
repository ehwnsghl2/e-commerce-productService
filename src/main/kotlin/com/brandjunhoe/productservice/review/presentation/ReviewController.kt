package com.brandjunhoe.productservice.review.presentation

import com.brandjunhoe.productservice.common.page.ReqPageDTO
import com.brandjunhoe.productservice.common.page.ResPageDTO
import com.brandjunhoe.productservice.common.response.CommonResponse
import com.brandjunhoe.productservice.review.application.ReviewService
import com.brandjunhoe.productservice.review.application.dto.ReviewDTO
import com.brandjunhoe.productservice.review.presentation.dto.ReqReviewSaveDTO
import org.springframework.web.bind.annotation.*

/**
 * Create by DJH on 2022/03/28.
 */
@RestController
@RequestMapping("/review")
class ReviewController(val reviewService: ReviewService) {


    @GetMapping
    fun findAllReviews(pageRequest: ReqPageDTO): CommonResponse<ResPageDTO<List<ReviewDTO>>> =
        CommonResponse(reviewService.findAll(pageRequest.getPageable()))


    @PostMapping
    fun saveReview(@RequestBody request: ReqReviewSaveDTO): CommonResponse<Unit> {
        request.validate()
        reviewService.save(request)
        return CommonResponse()
    }

}