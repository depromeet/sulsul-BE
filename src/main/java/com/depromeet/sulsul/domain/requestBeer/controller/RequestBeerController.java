package com.depromeet.sulsul.domain.requestBeer.controller;

import static com.depromeet.sulsul.util.PropertyUtil.getMemberIdFromPrincipal;

import com.depromeet.sulsul.common.response.dto.DescPageableResponseDto;
import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.domain.requestBeer.dto.RequestBeerRequestDto;
import com.depromeet.sulsul.domain.requestBeer.dto.RequestBeerResponseDto;
import com.depromeet.sulsul.domain.requestBeer.service.RequestBeerService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/request-beers")
@RequiredArgsConstructor
@Api(tags = "미등록 맥주 APIs")
public class RequestBeerController {

  private final RequestBeerService requestBeerService;

  @PostMapping
  public ResponseDto<RequestBeerResponseDto> save(@RequestBody RequestBeerRequestDto requestBeerRequestDto,
                                                  Authentication authentication) {
    return ResponseDto.from(requestBeerService.save(requestBeerRequestDto, getMemberIdFromPrincipal(authentication)));
  }

  @GetMapping("/{requestBeerId}")
  public DescPageableResponseDto<RequestBeerResponseDto> find(@PathVariable(required = false) Long requestBeerId,
                                                              Authentication authentication) {
    return requestBeerService.find(requestBeerId, getMemberIdFromPrincipal(authentication));
  }
}
