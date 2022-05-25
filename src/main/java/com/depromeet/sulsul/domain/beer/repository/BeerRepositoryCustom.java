package com.depromeet.sulsul.domain.beer.repository;

import com.depromeet.sulsul.common.request.ReadRequest;
import com.depromeet.sulsul.domain.beer.dto.BeerDetail;
import com.depromeet.sulsul.domain.beer.dto.BeerDto;
import com.depromeet.sulsul.domain.beer.dto.BeerSearchConditionRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeerRepositoryCustom {

  List<BeerDto> findAllWithPageableFilterSort(Long memberId, Long beerId,
      BeerSearchConditionRequest beerSearchConditionRequest);

  List<BeerDto> findPageWith(Long memberId, ReadRequest readRequest);

  BeerDetail findById(Long memberId, Long beerId);
}
