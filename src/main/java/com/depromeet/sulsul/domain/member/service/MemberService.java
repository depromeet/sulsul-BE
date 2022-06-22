package com.depromeet.sulsul.domain.member.service;

import com.depromeet.sulsul.common.error.exception.custom.MemberNotFoundException;
import com.depromeet.sulsul.domain.member.dto.MemberDto;
import com.depromeet.sulsul.domain.member.dto.MyPageRequestDto;
import com.depromeet.sulsul.domain.member.entity.Member;
import com.depromeet.sulsul.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class MemberService {

  private final MemberRepository memberRepository;

  @Transactional(readOnly = true)
  public MemberDto findById(final long id) {
    return memberRepository.selectById(id).orElseThrow(MemberNotFoundException::new);
  }

  public String updateNickname(Long id, MyPageRequestDto myPageRequestDto) {
    Member member = memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
    member.updateNickname(myPageRequestDto.getNickname());
    return member.getNickname();
  }

  @Transactional(readOnly = true)
  public Integer findTierByMemberId(Long id) {
    return memberRepository.selectByTierById(id);
  }

  public void updateDeletedAtById(Long id) {
    memberRepository.updateDeletedAtById(id);
  }
}
