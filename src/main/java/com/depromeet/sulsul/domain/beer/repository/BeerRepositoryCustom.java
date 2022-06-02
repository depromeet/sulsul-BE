package com.depromeet.sulsul.domain.beer.repository;

import com.depromeet.sulsul.common.request.ReadRequest;
import com.depromeet.sulsul.domain.beer.dto.BeerResponseDto;
import com.depromeet.sulsul.domain.beer.dto.BeerSearchConditionRequest;
import com.querydsl.core.Tuple;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerRepositoryCustom {

  List<BeerResponseDto> findAllWithPageableFilterSort(Long memberId, Long beerId,
      BeerSearchConditionRequest beerSearchConditionRequest);

  List<BeerResponseDto> findPageWith(Long memberId, ReadRequest readRequest);

  List<BeerResponseDto> findPageWith(Long memberId);

  List<BeerResponseDto> findBeerNotExistsRecord(Long memberId);

  List<BeerResponseDto> findBeerLikedByMemberId(Long memberId);

  Tuple findById(Long memberId, Long beerId);

  Integer countWithFilter(ReadRequest readRequest);

  Long findBeerCountByMemberId(Long id);
}
