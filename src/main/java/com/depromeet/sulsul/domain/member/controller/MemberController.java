package com.depromeet.sulsul.domain.member.controller;

import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.domain.member.dto.MemberDto;
import com.depromeet.sulsul.domain.member.facade.MemberFacade;
import com.depromeet.sulsul.domain.member.service.MemberService;
import com.depromeet.sulsul.oauth2.User;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
@Api(tags = "멤버 APIs (version 1)")
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;
  private final MemberFacade memberFacade;

  @GetMapping("/{id}")
  public ResponseDto<MemberDto> findMember(@PathVariable final Long id) {
    return ResponseDto.from(memberService.findMember(id));
  }

  @GetMapping("/level/{id}")
  public ResponseDto<?> findLevelByMemberId(@PathVariable Long id) {
    return ResponseDto.from(memberService.findLevelByMemberId(id));
  }

  @DeleteMapping
  public ResponseDto<?> deleteById(Authentication authentication) {
    User user = (User) authentication.getPrincipal();
    memberFacade.deleteMember(Long.parseUnsignedLong(user.getUsername()));
    return ResponseDto.OK();
  }
}
