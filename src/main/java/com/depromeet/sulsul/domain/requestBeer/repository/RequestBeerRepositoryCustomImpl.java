package com.depromeet.sulsul.domain.requestBeer.repository;

import static com.depromeet.sulsul.domain.requestBeer.entity.QRequestBeer.requestBeer;

import com.depromeet.sulsul.domain.requestBeer.dto.QRequestBeerResponseDto;
import com.depromeet.sulsul.domain.requestBeer.dto.RequestBeerResponseDto;
import com.depromeet.sulsul.util.PaginationUtil;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RequestBeerRepositoryCustomImpl implements RequestBeerRepositoryCustom{

  private final JPAQueryFactory queryFactory;

  @Override
  public List<RequestBeerResponseDto> findByMemberIdWithPageable(Long requestBeerId, Long memberId){
    return queryFactory.select(new QRequestBeerResponseDto(
            requestBeer.requestBeerId, requestBeer.beerName, requestBeer.beerImageUrlFirst, requestBeer.beerImageUrlSecond, requestBeer.requestCompletedAt, requestBeer.requestRejectionReason
            , requestBeer.status, requestBeer.createdAt
        ))
        .from(requestBeer)
        .where(
            requestBeer.member.id.eq(memberId)
            , requestBeerResponseIdLoe(requestBeerId)
        )
        .orderBy(requestBeer.requestBeerId.desc())
        .limit(PaginationUtil.PAGINATION_SIZE + 1L)
        .fetch();
  }

  private BooleanExpression requestBeerResponseIdLoe(Long requestBeerId){
    return requestBeerId != null ? requestBeer.requestBeerId.loe(requestBeerId) : null;
  }
}
