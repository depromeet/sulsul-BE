package com.depromeet.sulsul.domain.requestBeer.repository;

import com.depromeet.sulsul.domain.requestBeer.dto.QRequestBeerResponseDto;
import com.depromeet.sulsul.domain.requestBeer.dto.RequestBeerResponseDto;
import com.depromeet.sulsul.util.PaginationUtil;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static com.depromeet.sulsul.domain.requestBeer.entity.QRequestBeer.requestBeer;

@RequiredArgsConstructor
public class RequestBeerRepositoryCustomImpl implements RequestBeerRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  @Override
  public List<RequestBeerResponseDto> findByMemberIdWithPageable(Long requestBeerId, Long memberId) {
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

  @Override
  public void updateDeletedAtByMemberId(Long id) {
    queryFactory.update(requestBeer)
        .set(requestBeer.deletedAt, LocalDateTime.now())
        .where(requestBeer.member.id.eq(id))
        .execute();
  }

  private BooleanExpression requestBeerResponseIdLoe(Long requestBeerId) {
    return requestBeerId != null ? requestBeer.requestBeerId.loe(requestBeerId) : null;
  }
}
