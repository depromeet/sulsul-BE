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
  public List<RequestBeerResponseDto> findByMemberIdWithPageable(Long requestBeerResponseId, Long memberId){
    return queryFactory.select(new QRequestBeerResponseDto(
            requestBeer.beerName, requestBeer.beerImageUrl, requestBeer.requestCompletedAt, requestBeer.requestRejectionReason
            , requestBeer.status, requestBeer.createdAt
        ))
        .from(requestBeer)
        .where(
            requestBeer.member.id.eq(memberId)
            , requestBeerResponseIdGoe(requestBeerResponseId)
        )
        .orderBy(requestBeer.requestBeerId.asc())
        .limit(PaginationUtil.PAGINATION_SIZE + 1L)
        .fetch();
  }

  private BooleanExpression requestBeerResponseIdGoe(Long requestBeerResponseId){
    return requestBeerResponseId != null ? requestBeer.requestBeerId.goe(requestBeerResponseId) : null;
  }
}
