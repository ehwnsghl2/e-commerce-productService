package com.brandjunhoe.productservice.review.infra.impl

import com.brandjunhoe.productservice.review.application.dto.ReviewTotalDTO
import com.brandjunhoe.productservice.review.domain.QReview.review
import com.brandjunhoe.productservice.review.domain.ReviewCustomRepository
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

/**
 * Created by DJH to 2022/03/29
 */
@Repository
class ReviewCustomRepositoryImpl(private val queryFactory: JPAQueryFactory) : ReviewCustomRepository {


    override fun findByTotalReviewScoreAvgAndCount(productCode: String) {
        queryFactory.select(
            Projections.constructor(
                ReviewTotalDTO::class.java,
                review.score.avg(),
                review.count().intValue()
            )
        ).from(review)
            .where()

    }


}