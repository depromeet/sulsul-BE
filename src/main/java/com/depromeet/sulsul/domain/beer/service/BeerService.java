package com.depromeet.sulsul.domain.beer.service;

import com.depromeet.sulsul.common.request.ReadRequest;
import com.depromeet.sulsul.common.response.dto.PageableResponse;
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
  public PageableResponse<BeerDto> findPageWithFilterRequest(Long memberId, Long beerId,
      BeerSearchConditionRequest beerSearchConditionRequest) {
    List<BeerDto> beerDtosWithPageable = beerRepositoryCustom.findAllWithPageableFilterSort(
        memberId, beerId, beerSearchConditionRequest);

    PageableResponse<BeerDto> beerPageableResponse = new PageableResponse<>();
    if (isOverPaginationSize(beerDtosWithPageable)) {
      beerDtosWithPageable.remove(PAGINATION_SIZE);
      beerPageableResponse.setHasNext(true);
    }

    beerPageableResponse.setContents(beerDtosWithPageable);
    return beerPageableResponse;
  }

  @Transactional(readOnly = true)
  public PageableResponse<BeerDto> findPageWithReadRequest(Long memberId, ReadRequest readRequest) {
    List<BeerDto> beerDtosWithPageable = beerRepositoryCustom.findPageWith(memberId, readRequest);

    PageableResponse<BeerDto> beerPageableResponse = new PageableResponse<>();
    if (isOverPaginationSize(beerDtosWithPageable, readRequest.getLimit())) {
      beerDtosWithPageable.remove(readRequest.getLimit());
      setNextCursorInfo(beerPageableResponse, readRequest.getCursor(), readRequest.getLimit());
    }

    beerPageableResponse.setContents(beerDtosWithPageable);
    return beerPageableResponse;
  }

  private void setNextCursorInfo(PageableResponse<BeerDto> beerPageableResponse,
      Long cursor, int limit) {
    beerPageableResponse.setHasNext(true);
    if (cursor == null) {
      beerPageableResponse.setNextCursor(Integer.toUnsignedLong(limit));
      return;
    }
    beerPageableResponse.setNextCursor(cursor + limit);
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
