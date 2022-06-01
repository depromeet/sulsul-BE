package com.depromeet.sulsul.domain.member.controller;

import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.domain.member.dto.MyPageRequestDto;
import com.depromeet.sulsul.domain.member.facade.MemberFacade;
import com.depromeet.sulsul.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/profile")
public class MyPageController {

  private final MemberService memberService;
  private final MemberFacade memberFacade;

  @GetMapping("/{id}")
  public ResponseDto<MyPageRequestDto> findMyPageByMemberId(@PathVariable Long id) {
    return ResponseDto.from(memberFacade.findMyPageByMemberId(id));
  }

  @PutMapping("/{id}")
  public ResponseDto<?> updateName(@PathVariable Long id, @RequestBody MyPageRequestDto myPageRequestDto) {
    memberService.updateName(id, myPageRequestDto);
    return ResponseDto.OK();
  }
}
