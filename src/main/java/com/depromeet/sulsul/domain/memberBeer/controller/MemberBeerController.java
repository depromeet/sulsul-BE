package com.depromeet.sulsul.domain.memberBeer.controller;

import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.domain.memberBeer.service.MemberBeerService;
import com.depromeet.sulsul.util.PropertyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/beer/liked")
@Api(tags = "찜하기 APIs (version 1)")
public class MemberBeerController {

  private final MemberBeerService memberBeerService;

  @PostMapping("/{beerId}")
  @ApiOperation(value = "찜하기 API")
  public ResponseDto<Boolean> save(@PathVariable("beerId") Long beerId) {
    Long memberId = PropertyUtil.getMemberIdFromAuthentication();
    return ResponseDto.from(memberBeerService.save(beerId, memberId));
  }

  @ApiOperation(value = "찜하기 취소 API")
  @DeleteMapping("/{beerId}")
  public ResponseDto<Boolean> delete(@PathVariable("beerId") Long beerId) {
    Long memberId = PropertyUtil.getMemberIdFromAuthentication();
    return ResponseDto.from(memberBeerService.delete(beerId, memberId));
  }

  @ApiOperation(value = "해당 유저의 좋아요한 맥주 개수 조회 API")
  @GetMapping
  public ResponseDto<Long> findMemberBeerCountByMemberId() {
    Long memberId = PropertyUtil.getMemberIdFromAuthentication();
    return ResponseDto.from(memberBeerService.findMemberBeerCountByMemberId(memberId));
  }
}
