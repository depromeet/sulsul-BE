package com.depromeet.sulsul.domain.member.controller;

import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.domain.member.dto.MyPageRequestDto;
import com.depromeet.sulsul.domain.member.facade.MemberFacade;
import com.depromeet.sulsul.domain.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/profile")
@Api(tags = "마이페이지 APIs (version 1)")
public class MyPageController {

  private final MemberService memberService;
  private final MemberFacade memberFacade;

  @ApiOperation(value = "count 조회 API")
  @GetMapping
  public ResponseDto<MyPageRequestDto> findMyPageByMemberId() {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Long memberId = Long.parseLong(authentication.getName());
    return ResponseDto.from(memberFacade.findMyPageByMemberId(memberId));
  }

  @ApiOperation(value = "닉네임 update API")
  @PutMapping("/{id}")
  public ResponseDto<?> updateName(@PathVariable Long id,
      @RequestBody MyPageRequestDto myPageRequestDto) {
    memberService.updateName(id, myPageRequestDto);
    return ResponseDto.OK();
  }
}
