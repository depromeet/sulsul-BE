package com.depromeet.sulsul.domain.memberBeer.repository;

import static com.depromeet.sulsul.domain.memberBeer.entity.QMemberBeer.memberBeer;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberBeerRepositoryCustomImpl implements MemberBeerRepositoryCustom {
  private final JPAQueryFactory queryFactory;

  @Override
  public Long findMemberBeerCountByMemberId(Long memberId){
    return queryFactory
        .selectFrom(memberBeer)
        .where(memberBeer.member.id.eq(memberId)
              ,memberBeer.beer.deletedAt.isNull()
        )
        .stream()
        .count();
  }

}
