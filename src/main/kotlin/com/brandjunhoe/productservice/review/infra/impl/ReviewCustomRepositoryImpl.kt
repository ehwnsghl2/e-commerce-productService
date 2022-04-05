package com.brandjunhoe.productservice.review.infra.impl

import com.brandjunhoe.productservice.review.application.dto.ReviewTotalDTO
import com.brandjunhoe.productservice.review.domain.QReview.review
import com.brandjunhoe.productservice.review.domain.ReviewCustomRepository
import com.querydsl.core.types.Projections
import com.querydsl.core.types.dsl.MathExpressions
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

/**
 * Created by DJH to 2022/03/29
 */
@Repository
class ReviewCustomRepositoryImpl(private val queryFactory: JPAQueryFactory) : ReviewCustomRepository {


    override fun findByReviewSummary(productCode: String): ReviewTotalDTO {
        return queryFactory.select(
            Projections.constructor(
                ReviewTotalDTO::class.java,
                MathExpressions.round(review.score.avg(), 1),
                review.count().intValue()
            )
        ).from(review)
            .where(review.productCode.eq(productCode))
            .fetchFirst()
    }


}