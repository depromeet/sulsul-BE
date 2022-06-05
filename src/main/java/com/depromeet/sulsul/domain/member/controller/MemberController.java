package com.depromeet.sulsul.domain.member.controller;

import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.domain.beer.service.BeerService;
import com.depromeet.sulsul.domain.member.dto.MemberDto;
import com.depromeet.sulsul.domain.member.service.MemberService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
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
  private final BeerService beerService;

  @GetMapping("/{id}")
  public ResponseDto<MemberDto> findMember(@PathVariable final Long id) {
    return ResponseDto.from(memberService.findMember(id));
  }
  @GetMapping("/level/{id}")
  public ResponseDto<?> findLevelByMemberId(@PathVariable Long id) {
    return ResponseDto.from(memberService.findLevelByMemberId(id));
  }
}
