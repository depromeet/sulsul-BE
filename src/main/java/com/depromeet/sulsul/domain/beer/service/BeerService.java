package com.depromeet.sulsul.domain.beer.service;

import com.depromeet.sulsul.common.request.ReadRequest;
import com.depromeet.sulsul.common.response.dto.PageableResponseDto;
import com.depromeet.sulsul.domain.beer.dto.BeerDetail;
import com.depromeet.sulsul.domain.beer.dto.BeerDto;
import com.depromeet.sulsul.domain.beer.dto.BeerSearchConditionRequest;
import com.depromeet.sulsul.domain.beer.dto.BeerRequest;
import com.depromeet.sulsul.domain.beer.dto.BeerTypeValue;
import com.depromeet.sulsul.domain.beer.entity.Beer;
import com.depromeet.sulsul.domain.beer.entity.BeerType;
import com.depromeet.sulsul.domain.beer.repository.BeerRepository;
import com.depromeet.sulsul.domain.beer.repository.BeerRepositoryCustom;
import com.depromeet.sulsul.domain.country.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.depromeet.sulsul.util.PaginationUtil.PAGINATION_SIZE;
import static com.depromeet.sulsul.util.PaginationUtil.isOverPaginationSize;


@Service
@Transactional
@RequiredArgsConstructor
public class BeerService {

  private final BeerRepository beerRepository;
  private final BeerRepositoryCustom beerRepositoryCustom;
  private final CountryRepository countryRepository;

  @Transactional(readOnly = true)
  public PageableResponseDto<BeerDto> findPageWithFilterRequest(Long memberId, Long beerId,
      BeerSearchConditionRequest beerSearchConditionRequest) {
    List<BeerDto> beerDtosWithPageable = beerRepositoryCustom.findAllWithPageableFilterSort(
        memberId, beerId, beerSearchConditionRequest);

    PageableResponseDto<BeerDto> beerPageableResponseDto = new PageableResponseDto<>();
    if (isOverPaginationSize(beerDtosWithPageable)) {
      beerDtosWithPageable.remove(PAGINATION_SIZE);
      beerPageableResponseDto.setHasNext(true);
    }

    beerPageableResponseDto.setContents(beerDtosWithPageable);
    return beerPageableResponseDto;
  }

  @Transactional(readOnly = true)
  public PageableResponseDto<BeerDto> findPageWithReadRequest(Long memberId, ReadRequest request) {
    List<BeerDto> beerDtosWithPageable = beerRepositoryCustom.findPageWith(memberId, request);
    return PageableResponseDto.of(beerDtosWithPageable, request.getCursor(), request.getLimit());
  }

  @Transactional(readOnly = true)
  public PageableResponseDto<BeerDto> findAll(Long memberId) {

    return PageableResponseDto.of(beerRepositoryCustom.findPageWith(memberId), 0L, PAGINATION_SIZE);
  }

  public void save(BeerRequest beerRequest) {

    beerRepository.save(new Beer(
        countryRepository.getById(beerRequest.getCountryId()),
        beerRequest));
  }

  @Transactional(readOnly = true)
  public BeerDetail findById(Long memberId, Long beerId) {
    return beerRepositoryCustom.findById(memberId, beerId);
  }

  public List<BeerTypeValue> findTypes() {
    return Arrays
        .stream(BeerType.class.getEnumConstants())
        .map(BeerTypeValue::new)
        .collect(Collectors.toList());
  }
}
