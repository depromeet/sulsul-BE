package com.depromeet.sulsul.domain.review.service;


import com.depromeet.sulsul.domain.beer.repository.BeerRepository;
import com.depromeet.sulsul.domain.member.repository.MemberRepository;
import com.depromeet.sulsul.domain.review.dto.ReviewRequest;
import com.depromeet.sulsul.domain.review.dto.ReviewUpdateRequest;
import com.depromeet.sulsul.domain.review.entity.Review;
import com.depromeet.sulsul.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final BeerRepository beerRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void save(ReviewRequest reviewRequest){
        reviewRepository.save(new Review(
                reviewRequest.getContent()
                , memberRepository.getById(reviewRequest.getMemberId())
                , beerRepository.getById(reviewRequest.getBeerId())
        ));
    }

    @Transactional
    public void update(ReviewUpdateRequest reviewUpdateRequest){
        final Review targetReview = reviewRepository.getById(reviewUpdateRequest.getId());
        targetReview.updateReview(reviewUpdateRequest.getContent());
    }

    @Transactional
    public void delete(Long reviewId){
        final Review targetReview = reviewRepository.getById(reviewId);
        targetReview.deleteReview();
    }

}
