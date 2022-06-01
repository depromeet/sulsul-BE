package com.depromeet.sulsul.domain.member.repository;

import com.depromeet.sulsul.domain.member.dto.MemberDto;
import com.depromeet.sulsul.domain.member.entity.Member;

public interface MemberRepositoryCustom {

  MemberDto selectById(long id);
}
