package com.depromeet.sulsul.domain.member.repository;

import com.depromeet.sulsul.domain.member.dto.MemberDto;
import com.depromeet.sulsul.domain.member.entity.Member;
import java.util.Optional;

public interface MemberRepositoryCustom {

  Optional<MemberDto> selectById(long id);

  Optional<Member> selectBySocial(String id, String type);

  void updateDeletedAtById(Long id);

  Integer selectByTierById(Long id);
}
