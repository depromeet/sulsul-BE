package com.depromeet.sulsul.domain.beer.service;

import static com.depromeet.sulsul.util.PaginationUtil.PAGINATION_SIZE;
import static com.depromeet.sulsul.util.PaginationUtil.isOverPaginationSize;

import com.depromeet.sulsul.common.request.ReadRequest;
import com.depromeet.sulsul.common.response.dto.PageableResponseDto;
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
import com.depromeet.sulsul.domain.country.repository.CountryRepository;
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
    return beerRepositoryCustom.findById(memberId, beerId);
  }

  public List<BeerTypeValue> findTypes() {
    return Arrays
        .stream(BeerType.class.getEnumConstants())
        .map(BeerTypeValue::new)
        .collect(Collectors.toList());
  }

  public BeerResponsesDto findRecommands(Long memberId) {
    List<BeerResponseDto> beerResponseDtos = beerRepositoryCustom.findBeerNotExistsRecord(
        memberId);
    Collections.shuffle(beerResponseDtos);

    return BeerResponsesDto.from(new ArrayList<>(beerResponseDtos.subList(0, PAGINATION_SIZE)));
  }
}
