package com.depromeet.sulsul.domain.beer.controller;

import com.depromeet.sulsul.common.request.ReadRequest;
import com.depromeet.sulsul.common.response.dto.PageableResponseDto;
import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.domain.beer.dto.BeerResponseDto;
import com.depromeet.sulsul.domain.beer.dto.BeerTotalCountResponseDto;
import com.depromeet.sulsul.domain.beer.service.BeerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(tags = "맥주 APIs (version 2)")
@RequestMapping("/api/v2/beers")
public class BeerControllerV2 {

  private final BeerService beerService;

  @PostMapping
  @ApiOperation(value = "맥주 조회 API (검색/필터/정렬 포함)")
  public PageableResponseDto<BeerResponseDto> findPageWithFilterRequest(
      @RequestBody(required = false) @Validated ReadRequest readRequest) {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Long memberId = Long.parseLong(authentication.getName());
    if (readRequest == null) {
      return beerService.findAll(memberId);
    }
    return beerService.findPageWithReadRequest(memberId, readRequest);
  }

  @GetMapping("/recommend")
  @ApiOperation(value = "추천 맥주 조회 API")
  public ResponseDto<List<BeerResponseDto>> findRecommends() {
    Long memberId = 1L;
    return ResponseDto.from(beerService.findRecommends(memberId).getBeerResponseDtos());
  }

  @PostMapping("/liked")
  @ApiOperation(value = "반한 맥주 API")
  public PageableResponseDto<BeerResponseDto> findLikes(
      @RequestBody(required = false) @Validated ReadRequest readRequest) {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Long memberId = Long.parseLong(authentication.getName());
    return beerService.findLikes(memberId, readRequest);
  }

  @GetMapping("/count")
  @ApiOperation(value = "맥주 전체 개수 조회 API")
  public ResponseDto<BeerTotalCountResponseDto> countAllBeers() {
    return beerService.countAllBeers();
  }
}
