package com.depromeet.sulsul.domain.beer.repository;

import com.depromeet.sulsul.common.request.ReadRequest;
import com.depromeet.sulsul.domain.beer.dto.BeerDetailResponseDto;
import com.depromeet.sulsul.domain.beer.dto.BeerResponseDto;
import com.depromeet.sulsul.domain.beer.dto.BeerSearchConditionRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeerRepositoryCustom {

  List<BeerResponseDto> findAllWithPageableFilterSort(Long memberId, Long beerId,
      BeerSearchConditionRequest beerSearchConditionRequest);

  List<BeerResponseDto> findPageWith(Long memberId, ReadRequest readRequest);

  List<BeerResponseDto> findPageWith(Long memberId);

  BeerDetailResponseDto findById(Long memberId, Long beerId);
}
