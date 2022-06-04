package com.depromeet.sulsul.domain.memberLevel.repository;

import com.depromeet.sulsul.domain.memberLevel.dto.MemberLevelResponseDto;

public interface MemberLevelRepositoryCustom {

  MemberLevelResponseDto findMemberLevelByCount(Long count);

}
