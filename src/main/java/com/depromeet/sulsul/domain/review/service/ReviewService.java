package com.depromeet.sulsul.domain.review.service;


import com.depromeet.sulsul.domain.beer.dto.BeerDto;
import com.depromeet.sulsul.domain.beer.dto.BeerRequest;
import com.depromeet.sulsul.domain.beer.entity.Beer;
import com.depromeet.sulsul.domain.beer.repository.BeerRepository;
import com.depromeet.sulsul.domain.member.repository.MemberRepository;
import com.depromeet.sulsul.domain.review.dto.ReviewDeleteRequest;
import com.depromeet.sulsul.domain.review.dto.ReviewDto;
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

    public void save(ReviewRequest reviewRequest){
        reviewRepository.save(new Review(
            reviewRequest.getContent()
            , memberRepository.getById(reviewRequest.getMemberId())
            , beerRepository.getById(reviewRequest.getBeerId())
        ));
    }

    public void update(ReviewUpdateRequest reviewUpdateRequest){
        Review targetReview = reviewRepository.getById(reviewUpdateRequest.getId());
        targetReview = new Review(
                targetReview.getId()
                , reviewUpdateRequest.getContent()
                , targetReview.getIsDeleted()
                , targetReview.getMember()
                , targetReview.getBeer()
        );
    }

    public void delete(ReviewDeleteRequest reviewDeleteRequest){
        Review targetReview = reviewRepository.getById(reviewDeleteRequest.getId());
        targetReview = new Review(
                targetReview.getId()
                , targetReview.getContent()
                , true
                , targetReview.getMember()
                , targetReview.getBeer()
        );
    }

}
