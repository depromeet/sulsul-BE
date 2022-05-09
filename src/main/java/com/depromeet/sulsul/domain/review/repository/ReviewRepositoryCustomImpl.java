package com.depromeet.sulsul.domain.review.repository;

import com.depromeet.sulsul.domain.review.entity.Review;
import com.depromeet.sulsul.util.PaginationUtil;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.depromeet.sulsul.domain.review.entity.QReview.review;

@Repository
public class ReviewRepositoryCustomImpl implements ReviewRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public ReviewRepositoryCustomImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Review> findByIdWithPageable(Long beerId, Long reviewId){
        return queryFactory.select(review)
                .from(review)
                .where(review.beer.id.eq(beerId), review.id.goe(reviewId))
                .limit(PaginationUtil.PAGINATION_SIZE + 1)
                .fetch();
    }

    @Override
    public Long deleteByBeerId(Long beerId){
        return queryFactory.update(review)
                .set(review.isDeleted, review.isDeleted.isTrue())
                .where(review.beer.id.eq(beerId))
                .execute();
    }
}
