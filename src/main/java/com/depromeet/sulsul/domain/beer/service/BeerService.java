package com.depromeet.sulsul.domain.beer.service;

import static com.depromeet.sulsul.util.PaginationUtil.PAGINATION_SIZE;
import static com.depromeet.sulsul.util.PaginationUtil.isOverPaginationSize;
import static com.depromeet.sulsul.util.PropertyUtil.DEFAULT_START_COUNTRY_ENG;
import static com.depromeet.sulsul.util.PropertyUtil.DEFAULT_START_COUNTRY_KOR;
import static com.depromeet.sulsul.util.PropertyUtil.ONE;
import static com.depromeet.sulsul.util.PropertyUtil.TWO;
import static com.depromeet.sulsul.util.PropertyUtil.ZERO;

import com.depromeet.sulsul.common.error.exception.custom.BeerNotFoundException;
import com.depromeet.sulsul.common.request.ReadRequest;
import com.depromeet.sulsul.common.response.dto.PageableResponseDto;
import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.domain.beer.dto.BeerCountResponseDto;
import com.depromeet.sulsul.domain.beer.dto.BeerDetailResponseDto;
import com.depromeet.sulsul.domain.beer.dto.BeerRequestDto;
import com.depromeet.sulsul.domain.beer.dto.BeerResponseDto;
import com.depromeet.sulsul.domain.beer.dto.BeerResponsesDto;
import com.depromeet.sulsul.domain.beer.dto.BeerSearchConditionRequest;
import com.depromeet.sulsul.domain.beer.dto.BeerTypeValue;
import com.depromeet.sulsul.domain.beer.entity.Beer;
import com.depromeet.sulsul.domain.beer.entity.BeerType;
import com.depromeet.sulsul.domain.beer.repository.BeerRepository;
import com.depromeet.sulsul.domain.beer.repository.BeerRepositoryCustom;
import com.depromeet.sulsul.domain.country.dto.CountryNameDto;
import com.depromeet.sulsul.domain.country.entity.Country;
import com.depromeet.sulsul.domain.country.repository.CountryRepository;
import com.depromeet.sulsul.domain.memberBeer.entity.MemberBeer;
import com.depromeet.sulsul.domain.record.repository.RecordRepository;
import com.querydsl.core.Tuple;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class BeerService {

  private final BeerRepository beerRepository;
  private final BeerRepositoryCustom beerRepositoryCustom;
  private final CountryRepository countryRepository;
  private final RecordRepository recordRepository;

  @Transactional(readOnly = true)
  public PageableResponseDto<BeerResponseDto> findPageWithFilterRequest(Long memberId, Long beerId,
      BeerSearchConditionRequest beerSearchConditionRequest) {
    List<BeerResponseDto> beerResponseDtosWithPageable = beerRepositoryCustom.findAllWithPageableFilterSort(
        memberId, beerId, beerSearchConditionRequest);

    PageableResponseDto<BeerResponseDto> beerPageableResponseDto = new PageableResponseDto<>();
    if (isOverPaginationSize(beerResponseDtosWithPageable)) {
      beerResponseDtosWithPageable.remove(PAGINATION_SIZE);
      beerPageableResponseDto.setHasNext(true);
    }

    beerPageableResponseDto.setContents(beerResponseDtosWithPageable);
    return beerPageableResponseDto;
  }

  @Transactional(readOnly = true)
  public PageableResponseDto<BeerResponseDto> findPageWithReadRequest(Long memberId,
      ReadRequest request) {
    List<BeerResponseDto> beerResponseDtosWithPageable = beerRepositoryCustom.findPageWith(memberId,
        request);
    return PageableResponseDto.of(beerResponseDtosWithPageable, request.getCursor(),
        request.getLimit());
  }

  @Transactional(readOnly = true)
  public PageableResponseDto<BeerResponseDto> findAll(Long memberId) {

    return PageableResponseDto.of(beerRepositoryCustom.findPageWith(memberId), 0L, PAGINATION_SIZE);
  }

  public void save(BeerRequestDto beerRequestDto) {

    beerRepository.save(new Beer(
        countryRepository.getById(beerRequestDto.getCountryId()),
        beerRequestDto));
  }

  @Transactional(readOnly = true)
  public BeerDetailResponseDto findById(Long memberId, Long beerId) {
    Tuple tuple = beerRepositoryCustom.findById(memberId, beerId);

    if (tuple == null) {
      throw new BeerNotFoundException();
    }
    Country country = tuple.get(ZERO, Country.class);
    Beer beer = tuple.get(ONE, Beer.class);
    MemberBeer memberBeer = tuple.get(TWO, MemberBeer.class);

    Tuple endCountryOfRecentRecord = recordRepository.findEndCountryOfRecordByMemberId(
        memberId);

    if (endCountryOfRecentRecord == null) {
      return new BeerDetailResponseDto(country, beer, memberBeer,
          new CountryNameDto(DEFAULT_START_COUNTRY_KOR, DEFAULT_START_COUNTRY_ENG));
    }
    return new BeerDetailResponseDto(country, beer, memberBeer,
        new CountryNameDto(endCountryOfRecentRecord.get(ZERO, String.class),
            endCountryOfRecentRecord.get(ONE, String.class)));
  }

  public List<BeerTypeValue> findTypes() {
    return Arrays
        .stream(BeerType.class.getEnumConstants())
        .map(BeerTypeValue::new)
        .collect(Collectors.toList());
  }

  public BeerResponsesDto findRecommends(Long memberId) {
    List<BeerResponseDto> beerResponseDtos = beerRepositoryCustom.findBeerNotExistsRecord(
        memberId);
    Collections.shuffle(beerResponseDtos);

    return BeerResponsesDto.from(new ArrayList<>(beerResponseDtos.subList(0, PAGINATION_SIZE)));
  }

  public ResponseDto<BeerCountResponseDto> countWithFilterRequest(ReadRequest readRequest) {
    Long entireResultCount = beerRepository.count();
    if (readRequest == null) {
      return ResponseDto.from(new BeerCountResponseDto(entireResultCount, entireResultCount));
    }
    Long searchResultCount = (long) beerRepositoryCustom.countWithFilter(readRequest);
    return ResponseDto.from(new BeerCountResponseDto(searchResultCount, entireResultCount));
  }

  public Long findBeerCountByMemberId(Long id) {
    return beerRepositoryCustom.findBeerCountByMemberId(id);
  }

}