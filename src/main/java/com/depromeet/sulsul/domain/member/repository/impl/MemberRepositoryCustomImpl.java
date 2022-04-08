package com.depromeet.sulsul.domain.member.repository.impl;

import com.depromeet.sulsul.domain.member.repository.MemberRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {

  private final JPAQueryFactory jpaQueryFactory;

}
