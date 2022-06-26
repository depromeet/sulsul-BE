package com.depromeet.sulsul.domain.member.controller;

import static com.depromeet.sulsul.util.PropertyUtil.getMemberIdFromPrincipal;

import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.domain.member.dto.MyPageResponseDto;
import com.depromeet.sulsul.domain.member.dto.NicknameRequestDto;
import com.depromeet.sulsul.domain.member.facade.MemberFacade;
import com.depromeet.sulsul.domain.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/profile")
@Api(tags = "프로필 APIs (version 1)")
public class MyPageController {

  private final MemberService memberService;
  private final MemberFacade memberFacade;

  @ApiOperation(value = "count 조회 API")
  @GetMapping
  public ResponseDto<MyPageResponseDto> findMyPageByMemberId(Authentication authentication) {
    return ResponseDto.from(
        memberFacade.findMyPageByMemberId(getMemberIdFromPrincipal(authentication)));
  }

  @ApiOperation(value = "닉네임 update API")
  @PutMapping
  public ResponseDto<String> updateNickname(@RequestBody @Valid NicknameRequestDto NicknameRequestDto, Authentication authentication) {
    return ResponseDto.from(memberService.updateNickname(getMemberIdFromPrincipal(authentication), NicknameRequestDto));

  }
}
