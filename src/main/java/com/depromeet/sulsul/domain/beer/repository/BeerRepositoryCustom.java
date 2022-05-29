package com.depromeet.sulsul.domain.beer.repository;

import com.depromeet.sulsul.common.request.ReadRequest;
import com.depromeet.sulsul.domain.beer.dto.BeerDetailResponseDto;
import com.depromeet.sulsul.domain.beer.dto.BeerResponseDto;
import com.depromeet.sulsul.domain.beer.dto.BeerSearchConditionRequest;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerRepositoryCustom {

  List<BeerResponseDto> findAllWithPageableFilterSort(Long memberId, Long beerId,
      BeerSearchConditionRequest beerSearchConditionRequest);

  List<BeerResponseDto> findPageWith(Long memberId, ReadRequest readRequest);

  List<BeerResponseDto> findPageWith(Long memberId);

  List<BeerResponseDto> findBeerNotExistsRecord(Long memberId);

  BeerDetailResponseDto findById(Long memberId, Long beerId);

  Long findMemberBeerCount(Long id);
  Integer countWithFilter(ReadRequest readRequest);
}
