package com.depromeet.sulsul.domain.beer.repository;

import com.depromeet.sulsul.common.request.ReadRequest;
import com.depromeet.sulsul.domain.beer.dto.BeerResponseDto;
import com.depromeet.sulsul.domain.beer.dto.BeerResponseWithCountDto;
import com.depromeet.sulsul.domain.beer.dto.BeerSearchConditionRequest;
import com.querydsl.core.Tuple;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerRepositoryCustom {

  List<BeerResponseDto> findAllWithPageableFilterSort(Long memberId, Long beerId,
      BeerSearchConditionRequest beerSearchConditionRequest);

  List<BeerResponseDto> findPageWith(Long memberId, ReadRequest request);

  BeerResponseWithCountDto findPageWithV2(Long memberId, ReadRequest request);

  List<BeerResponseDto> findPageWith(Long memberId);

  List<BeerResponseDto> findBeerNotExistsRecord(Long memberId);

  List<BeerResponseDto> findBeerLikedByMemberId(Long memberId);

  BeerResponseWithCountDto findBeerLikedByMemberIdV2(Long memberId, ReadRequest request);

  Tuple findById(Long memberId, Long beerId);

  Integer countWithFilter(ReadRequest request);

  Long findBeerCountByMemberId(Long id);
}
