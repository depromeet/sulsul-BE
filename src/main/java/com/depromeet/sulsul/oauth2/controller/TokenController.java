package com.depromeet.sulsul.oauth2.controller;

import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.oauth2.service.JwtTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "토큰 APIs")
@RestController
@RequestMapping("/api/v1/token")
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
