package com.depromeet.sulsul.domain.memberLevel.service;

import com.depromeet.sulsul.domain.memberLevel.dto.MemberLevelResponseDto;
import com.depromeet.sulsul.domain.memberLevel.entity.MemberLevel;
import com.depromeet.sulsul.domain.memberLevel.repository.MemberLevelRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class MemberLevelService {
  private final MemberLevelRepository memberLevelRepository;

  @Transactional(readOnly = true)
  public List<MemberLevelResponseDto> findAll() {
    return  memberLevelRepository
                                .findAll()
                                .stream()
                                .map(MemberLevel::toDto)
                                .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public MemberLevelResponseDto findMemberLevelByCount(Long memberId){
    return memberLevelRepository.findMemberLevelByCount(memberId);
  }

}
