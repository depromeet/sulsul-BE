package com.depromeet.sulsul.domain.memberLevel.controller;

import static com.depromeet.sulsul.util.PropertyUtil.getMemberIdFromPrincipal;

import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.domain.memberLevel.dto.MemberLevelResponseDto;
import com.depromeet.sulsul.domain.memberLevel.service.MemberLevelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/member-levels")
@Api(tags = "멤버 레벨 APIs (version 1)")
@RequiredArgsConstructor
public class MemberLevelController {

  private final MemberLevelService memberLevelService;

  @ApiOperation(value = "전체 level 정보 조회 API")
  @GetMapping("/all")
  public ResponseDto<List<MemberLevelResponseDto>> findAll() {
    return ResponseDto.from(memberLevelService.findAll());
  }

  @ApiOperation(value = "기록수에 따른 level 조회 API")
  @GetMapping("/{count}")
  public ResponseDto<MemberLevelResponseDto> findMemberLevelByCount(@PathVariable Long count) {
    return ResponseDto.from(memberLevelService.findMemberLevelByCount(count));
  }

  @ApiOperation(value = "해당 유저의 level 조회 API")
  @GetMapping
  public ResponseDto<MemberLevelResponseDto> find(Authentication authentication) {
    return ResponseDto.from(memberLevelService.find(getMemberIdFromPrincipal(authentication)));
  }

}
