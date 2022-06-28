package com.depromeet.sulsul.domain.token.controller;

import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.domain.token.service.JwtTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "토큰 APIs")
@RequestMapping("/api/v1/token")
@RequiredArgsConstructor
public class TokenController {

  private final JwtTokenService jwtTokenService;

  @ApiOperation(value = "토큰 갱신", notes = "refresh 토큰으로 accessToken 갱신")
  @GetMapping("/refresh")
  public ResponseDto<?> updateRefreshToken(HttpServletResponse response,
                                           @CookieValue(name = "refreshToken", required = false) String refreshToken) {
    return ResponseDto.from(jwtTokenService.publishAccessToken(response, refreshToken));
  }

  @ApiOperation(value = "토큰 갱신(Request Header)", notes = "refresh 토큰으로 accessToken 갱신 (Request Header)")
  @GetMapping("/refresh/use-header")
  public ResponseDto<?> updateRefreshTokenUseHeader(HttpServletResponse response,
      @RequestHeader(name = "refreshToken", required = false) String refreshToken) {
    jwtTokenService.publishAccessToken(response, refreshToken);
    return ResponseDto.from(jwtTokenService.publishAccessToken(response, refreshToken));
  }
}
