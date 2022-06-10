package com.depromeet.sulsul.domain.beer.controller;

import com.depromeet.sulsul.common.request.ReadRequest;
import com.depromeet.sulsul.common.response.dto.PageableResponseDto;
import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.domain.beer.dto.BeerDetailResponseDto;
import com.depromeet.sulsul.domain.beer.dto.BeerResponseDto;
import com.depromeet.sulsul.domain.beer.dto.BeerTotalCountResponseDto;
import com.depromeet.sulsul.domain.beer.dto.BeerTypeValue;
import com.depromeet.sulsul.domain.beer.service.BeerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(tags = "맥주 APIs (게스트용)")
@RequestMapping("/guest/api/v1/beers")
public class GuestBeerController {

  private final BeerService beerService;

  @PostMapping
  @ApiOperation(value = "맥주 조회 API (검색/필터/정렬 포함)")
  public PageableResponseDto<BeerResponseDto> findPageWithFilterRequest(
      @RequestBody(required = false) @Validated ReadRequest readRequest) {
    if (readRequest == null) {
      return beerService.findAll();
    }
    return beerService.findPageWithReadRequestV2(readRequest);
  }

  @GetMapping("/recommend")
  @ApiOperation(value = "추천 맥주 리스트 조회 API")
  public ResponseDto<List<BeerResponseDto>> findRecommends() {
    return ResponseDto.from(beerService.findRecommends().getBeerResponseDtos());
  }

  @GetMapping("/{beerId}")
  @ApiOperation(value = "맥주 상세 조회 API")
  public ResponseDto<BeerDetailResponseDto> findById(@PathVariable("beerId") Long beerId) {
    return ResponseDto.from(beerService.findById(beerId));
  }

  @GetMapping("/types")
  @ApiOperation(value = "맥주 종류 전체 조회 API")
  public ResponseDto<List<BeerTypeValue>> findTypes() {
    return ResponseDto.from(beerService.findTypes());
  }

  @GetMapping("/count")
  @ApiOperation(value = "맥주 전체 개수 조회 API")
  public ResponseDto<BeerTotalCountResponseDto> countAllBeers() {
    return beerService.countAllBeers();
  }
}
