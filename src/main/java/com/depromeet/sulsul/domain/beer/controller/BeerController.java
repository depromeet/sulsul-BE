package com.depromeet.sulsul.domain.beer.controller;

import com.depromeet.sulsul.common.request.ReadRequest;
import com.depromeet.sulsul.common.response.dto.PageableResponseDto;
import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.domain.beer.dto.*;
import com.depromeet.sulsul.domain.beer.entity.BeerType;
import com.depromeet.sulsul.domain.beer.entity.SortType;
import com.depromeet.sulsul.domain.beer.service.BeerService;
import com.depromeet.sulsul.oauth2.CustomOAuth2User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(tags = "맥주 APIs (version 1)")
@RequestMapping("/api/v1/beers")
public class BeerController {

  private final BeerService beerService;

  @GetMapping
  @ApiOperation(value = "(deprecated) 맥주 조회 API (검색/필터/정렬 포함)")
  public PageableResponseDto<BeerResponseDto> findPageWithFilterRequest(
      @RequestParam("beerId") Long beerId,
      @RequestParam(required = false) List<BeerType> beerTypes,
      @RequestParam(required = false) List<Long> countryIds,
      @RequestParam(required = false) SortType sortType,
      @RequestParam(value = "keyword", required = false) String searchKeyword
  ) {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Long memberId = Long.parseLong(authentication.getName());
    BeerSearchConditionRequest beerSearchConditionRequest = new BeerSearchConditionRequest(
        beerTypes, countryIds, sortType, searchKeyword);
    return beerService.findPageWithFilterRequest(memberId, beerId, beerSearchConditionRequest);
  }

  @GetMapping("/recommend")
  @ApiOperation(value = "추천 맥주 리스트 조회 API")
  public ResponseDto<BeerResponsesDto> findRecommends() {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Long memberId = Long.parseLong(authentication.getName());

    return ResponseDto.from(beerService.findRecommends(memberId));
  }

  @GetMapping("/liked")
  @ApiOperation(value = "추천 맥주 리스트 조회(반한 맥주) API")
  public ResponseDto<BeerResponsesDto> findLikedRecommends() {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Long memberId = Long.parseLong(authentication.getName());
    return ResponseDto.from(beerService.findLikedRecommends(memberId, true));
  }

  @PostMapping("/count")
  @ApiOperation(value = "맥주 검색결과/전체 개수 조회 API")
  public ResponseDto<BeerCountResponseDto> countWithFilterRequest(
      @RequestBody(required = false) @Validated ReadRequest readRequest) {
    return beerService.countWithFilterRequest(readRequest);
  }

  @GetMapping("/{beerId}")
  @ApiOperation(value = "맥주 상세 조회 API")
  public ResponseDto<BeerDetailResponseDto> findById(@PathVariable("beerId") Long beerId) {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Long memberId = Long.parseLong(authentication.getName());
    return ResponseDto.from(beerService.findById(memberId, beerId));
  }

  @GetMapping("/types")
  @ApiOperation(value = "맥주 종류 전체 조회 API")
  public ResponseDto<List<BeerTypeValue>> findTypes() {
    return ResponseDto.from(beerService.findTypes());
  }

  //TODO: 로그인 기능 개발 후 권한 관련 수정 필요 (관리자용 기능)
  @PostMapping
  @ApiOperation("맥주 등록 API (관리자용)")
  public ResponseEntity save(@RequestBody BeerRequestDto beerRequestDto) {
    beerService.save(beerRequestDto);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
