package com.depromeet.sulsul.domain.member.controller;

import static com.depromeet.sulsul.util.PropertyUtil.getMemberIdFromPrincipal;

import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.domain.member.dto.MemberDto;
import com.depromeet.sulsul.domain.member.facade.MemberFacade;
import com.depromeet.sulsul.domain.member.service.MemberService;
import com.depromeet.sulsul.oauth2.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
@Api(tags = "멤버 APIs (version 1)")
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;
  private final MemberFacade memberFacade;

  @ApiOperation(value = "로그인되어 있는 사용자 정보 검색")
  @GetMapping
  public ResponseDto<MemberDto> findMember(Authentication authentication) {
    User user = (User) authentication.getPrincipal();
    return ResponseDto.from(memberService.findById(Long.parseLong(user.getUsername())));
  }

  @GetMapping("/level")
  public ResponseDto<Long> findLevelByMemberId(Authentication authentication) {
    return ResponseDto.from(memberService.findLevelByMemberId(getMemberIdFromPrincipal(authentication)));
  }

  @ApiOperation(value = "로그인되어 있는 사용자 회원 탈퇴")
  @DeleteMapping
  public ResponseDto<?> deleteById(Authentication authentication) {
    User user = (User) authentication.getPrincipal();
    memberFacade.deleteMember(Long.parseUnsignedLong(user.getUsername()));
    return ResponseDto.OK();
  }
}
