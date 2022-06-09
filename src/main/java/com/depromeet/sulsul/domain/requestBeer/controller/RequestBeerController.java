package com.depromeet.sulsul.domain.requestBeer.controller;

import com.depromeet.sulsul.common.response.dto.DescPageableResponseDto;
import com.depromeet.sulsul.common.response.dto.PageableResponseDto;
import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.domain.requestBeer.dto.RequestBeerRequestDto;
import com.depromeet.sulsul.domain.requestBeer.dto.RequestBeerResponseDto;
import com.depromeet.sulsul.domain.requestBeer.service.RequestBeerService;
import io.swagger.annotations.Api;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
  public ResponseDto<RequestBeerResponseDto> save(@RequestBody RequestBeerRequestDto requestBeerRequestDto){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Long memberId = Long.parseLong(authentication.getName());
    return ResponseDto.from(requestBeerService.save(requestBeerRequestDto, memberId));
  }

  @GetMapping("/{requestBeerId}")
  public PageableResponseDto<RequestBeerResponseDto> find(@PathVariable(required = false) Long requestBeerId){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Long memberId = Long.parseLong(authentication.getName());
    return requestBeerService.find(requestBeerId, memberId);
  }
}
