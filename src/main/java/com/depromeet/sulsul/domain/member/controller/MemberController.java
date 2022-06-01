package com.depromeet.sulsul.domain.member.controller;

import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.domain.beer.dto.BeerResponsesDto;
import com.depromeet.sulsul.domain.beer.service.BeerService;
import com.depromeet.sulsul.domain.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<Object> findMember(@PathVariable final Long id) {
    return ResponseEntity.ok().body(ResponseDto.from(memberService.findMember(id)));
  }

  @GetMapping("/{id}/beers")
  @ApiOperation(value = "반한 맥주 리스트 조회 API")
  public ResponseDto<BeerResponsesDto> findLikedRecommends() {
    Long memberId = 1L;
    return ResponseDto.from(beerService.findLikedRecommends(memberId, false));
  }
}
