package com.depromeet.sulsul.domain.beer.controller;

import com.depromeet.sulsul.common.request.ReadRequest;
import com.depromeet.sulsul.common.response.dto.PageableResponseDto;
import com.depromeet.sulsul.domain.beer.dto.BeerResponseDto;
import com.depromeet.sulsul.domain.beer.service.BeerService;
import com.depromeet.sulsul.util.PropertyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(tags = "맥주 APIs (version 3)")
@RequestMapping("/api/v3/beers")
public class BeerControllerV3 {

  private final BeerService beerService;

  @PostMapping
  @ApiOperation(value = "맥주 조회 API (검색/필터/정렬 포함)")
  public PageableResponseDto<BeerResponseDto> findPageWithFilterRequest(
      @RequestBody(required = false) @Validated ReadRequest readRequest) {

    Long memberId = PropertyUtil.getMemberIdFromAuthentication();
    if (readRequest == null) {
      return beerService.findAll(memberId);
    }
    return beerService.findPageWithReadRequestV2(memberId, readRequest);
  }
}
