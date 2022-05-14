package com.depromeet.sulsul.domain.review.controller;

import com.depromeet.sulsul.domain.beer.entity.Beer;
import com.depromeet.sulsul.domain.beer.entity.BeerType;
import com.depromeet.sulsul.domain.beer.repository.BeerRepository;
import com.depromeet.sulsul.domain.continent.entity.Continent;
import com.depromeet.sulsul.domain.continent.repository.ContinentRepository;
import com.depromeet.sulsul.domain.country.entity.Country;
import com.depromeet.sulsul.domain.country.repository.CountryRepository;
import com.depromeet.sulsul.domain.member.entity.Member;
import com.depromeet.sulsul.domain.member.repository.MemberRepository;
import com.depromeet.sulsul.domain.review.dto.ReviewRequest;
import com.depromeet.sulsul.domain.review.dto.ReviewUpdateRequest;
import com.depromeet.sulsul.domain.review.entity.Review;
import com.depromeet.sulsul.domain.review.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ReviewControllerTest {

    @Autowired
    ReviewController reviewController;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    EntityManager entityManager;

    @Autowired
    ContinentRepository continentRepository;
    @Autowired
    CountryRepository countryRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    BeerRepository beerRepository;

    @Test
    public void 리뷰작성테스트() throws Exception {
        //given
        /**
         * 기초 세팅
         */
        Continent continent = new Continent(1L, "아시아");
        Continent save1 = continentRepository.save(continent);
        entityManager.flush();
        entityManager.clear();
        Country country = new Country(1L, save1, "한국");
        Country save3 = countryRepository.save(country);
        Member member = new Member(1L, "fsfsfs", "류찬", "vdavadv", "vdavav");
        Member save4 = memberRepository.save(member);
        Beer beer = new Beer(1L, save3, BeerType.IPA, "찬맥주", "사진", "차가운맥주", 12.3f, 15000, 100);
        Beer save5 = beerRepository.save(beer);
        entityManager.flush();
        entityManager.clear();

        ReviewRequest reviewRequest = new ReviewRequest("이야 이거 맛있다", save4.getId(), save5.getId());
        Member byId1 = memberRepository.getById(reviewRequest.getMemberId());
        Beer byId = beerRepository.getById(reviewRequest.getMemberId());
        System.out.println(byId + ":" + byId1);

        ResponseEntity<Object> save = reviewController.save(reviewRequest);
        System.out.println(save.getStatusCode());

        entityManager.flush();
        entityManager.clear();

        List<Review> all = reviewRepository.findAll();
        entityManager.flush();
        entityManager.clear();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
        for (Review review : all) {
            System.out.println(review.getContent());
            ReviewUpdateRequest reviewUpdateRequest = new ReviewUpdateRequest(review.getId(), "다시보니 맛없는걸", 1L, 1L);
            ResponseEntity<Object> save2 = reviewController.update(reviewUpdateRequest);
            entityManager.flush();
            entityManager.clear();
            System.out.println(save2.getStatusCode());
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~");


        List<Review> all2 = reviewRepository.findAll();
        entityManager.flush();
        entityManager.clear();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
        for (Review review : all2) {
            System.out.println(review.getContent());
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
//
//        entityManager.flush();
//        entityManager.clear();
//
//        ReviewUpdateRequest reviewUpdateRequest = new ReviewUpdateRequest(1L, "다시보니 맛없는걸", 1L, 1L);
//        ResponseEntity<Object> save2 = reviewController.update(reviewUpdateRequest);
//        System.out.println(save2.getStatusCode());
//
//        entityManager.flush();
//        entityManager.clear();
//
//        Review result2 = reviewRepository.getById(1L);
//        System.out.println(result2);
//
//        entityManager.flush();
//        entityManager.clear();

        //when

        //then

    }

}