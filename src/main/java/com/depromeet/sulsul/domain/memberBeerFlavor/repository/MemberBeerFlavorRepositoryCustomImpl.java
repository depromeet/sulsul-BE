package com.depromeet.sulsul.domain.memberBeerFlavor.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import static com.depromeet.sulsul.domain.memberBeerFlavor.entity.QMemberBeerFlavor.memberBeerFlavor;

@RequiredArgsConstructor
public class MemberBeerFlavorRepositoryCustomImpl implements MemberBeerFlavorRepositoryCustom {

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public void updateDeletedAtMemberId(Long id) {
    jpaQueryFactory.update(memberBeerFlavor)
        .set(memberBeerFlavor.deletedAt, LocalDateTime.now())
        .where(memberBeerFlavor.member.id.eq(id))
        .execute();
  }
}
