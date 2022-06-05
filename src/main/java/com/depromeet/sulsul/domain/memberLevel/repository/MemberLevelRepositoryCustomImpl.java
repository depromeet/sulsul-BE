package com.depromeet.sulsul.domain.memberLevel.repository;

import static com.depromeet.sulsul.domain.member.entity.QMember.member;
import static com.depromeet.sulsul.domain.memberLevel.entity.QMemberLevel.memberLevel;

import com.depromeet.sulsul.domain.member.entity.QMember;
import com.depromeet.sulsul.domain.memberLevel.dto.MemberLevelResponseDto;
import com.depromeet.sulsul.domain.memberLevel.dto.QMemberLevelResponseDto;
import com.depromeet.sulsul.domain.memberLevel.entity.QMemberLevel;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberLevelRepositoryCustomImpl implements MemberLevelRepositoryCustom {
  private final JPAQueryFactory queryFactory;

  @Override
  public MemberLevelResponseDto findMemberLevelByCount(Long count){
    return queryFactory.select(new QMemberLevelResponseDto(
          memberLevel.id, memberLevel.tier, memberLevel.imageUrl, memberLevel.req
        ))
        .from(memberLevel)
        .where(memberLevel.req.loe(count))
        .orderBy(memberLevel.req.desc())
        .fetchFirst();
  }

  @Override
  public MemberLevelResponseDto findMemberLevel(Long memberId){
    return queryFactory.select(new QMemberLevelResponseDto(
            memberLevel.id, memberLevel.tier, memberLevel.imageUrl, memberLevel.req
        ))
        .from(member)
        .where(member.id.eq(memberId))
        .fetchFirst();
  }
}
