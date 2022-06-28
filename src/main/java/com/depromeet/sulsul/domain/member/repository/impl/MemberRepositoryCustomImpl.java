package com.depromeet.sulsul.domain.member.repository.impl;

import static com.depromeet.sulsul.domain.member.entity.QMember.member;
import static com.depromeet.sulsul.domain.memberLevel.entity.QMemberLevel.memberLevel;

import com.depromeet.sulsul.domain.member.dto.MemberDto;
import com.depromeet.sulsul.domain.member.dto.QMemberDto;
import com.depromeet.sulsul.domain.member.dto.SocialType;
import com.depromeet.sulsul.domain.member.entity.Member;
import com.depromeet.sulsul.domain.member.repository.MemberRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.time.LocalDateTime;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  @Override
  public Optional<MemberDto> selectById(long id) {
    return Optional.ofNullable(queryFactory
        .select(new QMemberDto(member.id, member.role.stringValue(), member.email, member.nickname, member.profileUrl,
            member.phoneNumber, member.memberLevel))
        .from(member)
        .leftJoin(member.memberLevel, memberLevel)
        .where(member.id.eq(id), member.deletedAt.isNull())
        .fetchOne());
  }

  @Override
  public Optional<Member> selectBySocial(String id, String type) {
    return Optional.ofNullable(queryFactory
        .selectFrom(member)
        .where(member.socialId.eq(id), member.socialType.eq(SocialType.valueOf(type)), member.deletedAt.isNull())
        .fetchOne());
  }

  @Override
  public void updateDeletedAtById(Long id) {
    queryFactory
        .update(member)
        .set(member.deletedAt, LocalDateTime.now())
        .where(member.id.eq(id), member.deletedAt.isNull())
        .execute();
  }
}