package com.depromeet.sulsul.domain.member.controller;

import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.domain.member.dto.MemberDto;
import com.depromeet.sulsul.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

  @GetMapping("/{id}")
  public ResponseDto<MemberDto> findMember(@PathVariable final Long id) {
    return ResponseDto.from(memberService.findMember(id));
  }
  @GetMapping("/level/{id}")
  public ResponseDto<?> findLevelByMemberId(@PathVariable Long id) {
    return ResponseDto.from(memberService.findLevelByMemberId(id));
  }
}
