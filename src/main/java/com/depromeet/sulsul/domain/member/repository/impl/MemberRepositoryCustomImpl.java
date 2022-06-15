package com.depromeet.sulsul.domain.member.repository.impl;

import com.depromeet.sulsul.domain.member.dto.MemberDto;
import com.depromeet.sulsul.domain.member.dto.QMemberDto;
import com.depromeet.sulsul.domain.member.dto.SocialType;
import com.depromeet.sulsul.domain.member.entity.Member;
import com.depromeet.sulsul.domain.member.repository.MemberRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.depromeet.sulsul.domain.member.entity.QMember.member;

@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  @Override
  public Optional<MemberDto> selectById(long id) {
    return Optional.ofNullable(queryFactory
        .select(new QMemberDto(member.id, member.role.stringValue(), member.email, member.name, member.profileUrl, member.phoneNumber, member.memberLevel))
        .from(member)
        .where(member.id.eq(id))
        .fetchOne());
  }

  @Override
  public Optional<Member> selectByEmailAndSocial(String email, String social) {
    return Optional.ofNullable(queryFactory
        .selectFrom(member)
        .where(member.email.eq(email),member.social.eq(SocialType.valueOf(social)))
        .fetchOne()
    );
  }

  @Override
  public void updateDeletedAtById(Long id) {
    queryFactory
        .update(member)
        .set(member.deletedAt, LocalDateTime.now())
        .where(member.id.eq(id))
        .execute();
  }
}