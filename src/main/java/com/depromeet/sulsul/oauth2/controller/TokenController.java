package com.depromeet.sulsul.oauth2.controller;

import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.oauth2.service.JwtTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Api(tags = {"토큰 관련"})
@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class TokenController {

  private final JwtTokenService jwtTokenService;

  @ApiOperation(value = "토큰 갱신", notes = "refresh 토큰으로 accessToken 갱신")
  @GetMapping("/refresh")
  public ResponseDto<?> updateRefreshToken(HttpServletResponse response,
                                           @CookieValue(name = "refreshToken", required = false) String refreshToken) {
        jwtTokenService.publishAccessToken(response, refreshToken);
        return ResponseDto.OK();
  }
}
