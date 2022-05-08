package com.depromeet.sulsul.domain.record.service;

import com.depromeet.sulsul.domain.beer.entity.Beer;
import com.depromeet.sulsul.domain.beer.entity.BeerType;
import com.depromeet.sulsul.domain.beer.repository.BeerRepository;
import com.depromeet.sulsul.domain.beerFlavor.service.BeerFlavorService;
import com.depromeet.sulsul.domain.continent.entity.Continent;
import com.depromeet.sulsul.domain.continent.repository.ContinentRepository;
import com.depromeet.sulsul.domain.country.entity.Country;
import com.depromeet.sulsul.domain.country.repository.CountryRepository;
import com.depromeet.sulsul.domain.flavor.entity.Flavor;
import com.depromeet.sulsul.domain.flavor.repository.FlavorRepository;
import com.depromeet.sulsul.domain.member.entity.Member;
import com.depromeet.sulsul.domain.member.repository.MemberRepository;
import com.depromeet.sulsul.domain.record.controller.RecordController;
import com.depromeet.sulsul.domain.record.dto.RecordDto;
import com.depromeet.sulsul.domain.record.entity.Record;
import com.depromeet.sulsul.domain.record.repository.RecordRepository;
import com.depromeet.sulsul.domain.recordFlavor.entity.RecordFlavor;
import com.depromeet.sulsul.domain.recordFlavor.repository.RecordFlavorRepository;
import com.depromeet.sulsul.domain.review.controller.ReviewController;
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
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class RecordServiceTest {

    @Autowired
    ReviewController reviewController;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    RecordService recordService;

    @Autowired
    RecordController recordController;

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    FlavorRepository flavorRepository;

    @Autowired
    RecordFlavorRepository recordFlavorRepository;

    @Autowired
    BeerFlavorService beerFlavorService;

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

        Record record = new Record("리뷰 1234", 5, 5, save4, save5);
        recordRepository.save(record);
        Flavor flavor = new Flavor(1L, "달달허이");
        Flavor flavor2 = new Flavor(2L, "쌉쌀허이");
        flavorRepository.save(flavor);
        flavorRepository.save(flavor2);
        entityManager.flush();
        entityManager.clear();

        RecordFlavor recordFlavor = new RecordFlavor(1L, record, flavor);
        RecordFlavor recordFlavor2 = new RecordFlavor(2L, record, flavor2);
        recordFlavorRepository.save(recordFlavor);
        recordFlavorRepository.save(recordFlavor2);
        entityManager.flush();
        entityManager.clear();


        Record record2 = new Record("맛있네용", 3, 4, save4, save5);
        Flavor flavor3 = new Flavor(3L, "우우우우");
        Flavor flavor4 = new Flavor(4L, "어어어어");
        Flavor flavor5 = new Flavor(5L, "아아아아");
        recordRepository.save(record2);
        flavorRepository.save(flavor3);
        flavorRepository.save(flavor4);
        flavorRepository.save(flavor5);
        entityManager.flush();
        entityManager.clear();

        RecordFlavor recordFlavor3 = new RecordFlavor(3L, record2, flavor3);
        RecordFlavor recordFlavor4 = new RecordFlavor(4L, record2, flavor4);
        RecordFlavor recordFlavor5 = new RecordFlavor(5L, record2, flavor5);
        recordFlavorRepository.save(recordFlavor3);
        recordFlavorRepository.save(recordFlavor4);
        recordFlavorRepository.save(recordFlavor5);
        entityManager.flush();
        entityManager.clear();

        List<RecordDto> recordDtos = recordService.findAllRecordsWithPageable(1L, 0L);
        List<String> changer = recordDtos.stream().map(RecordDto::getFlavor).collect(Collectors.toList());
//        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//        for (RecordDto recordDto : recordDtos) {
//            System.out.println(recordDto.getContent());
//        }
//        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        for (String s : changer) {
            System.out.println(s);
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        entityManager.flush();
        entityManager.clear();


    }
}