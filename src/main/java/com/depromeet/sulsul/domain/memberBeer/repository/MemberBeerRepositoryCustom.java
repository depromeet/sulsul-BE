package com.depromeet.sulsul.domain.memberBeer.repository;

public interface MemberBeerRepositoryCustom {

  Long findMemberBeerCountByMemberId(Long memberId);

  void updateDeletedAtByMemberId(Long id);
}
