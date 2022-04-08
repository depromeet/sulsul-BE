package com.depromeet.sulsul.domain.member.service;

import com.depromeet.sulsul.common.error.exception.custom.MemberNotFoundException;
import com.depromeet.sulsul.domain.member.entity.Member;
import com.depromeet.sulsul.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  public Member findMember(final long id) {
    return memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
  }
}
