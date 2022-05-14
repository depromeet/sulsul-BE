package com.depromeet.sulsul.domain.review.repository;

import com.depromeet.sulsul.domain.review.entity.Review;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepositoryCustom {
    List<Review> findByIdWithPageable(Long beerId, Long reviewId);
    Long deleteByBeerId(Long beerId);
}
