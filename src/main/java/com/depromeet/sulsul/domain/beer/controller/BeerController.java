package com.depromeet.sulsul.domain.beer.controller;

import com.depromeet.sulsul.common.response.dto.PageableResponseDto;
import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.domain.beer.dto.BeerDetailResponseDto;
import com.depromeet.sulsul.domain.beer.dto.BeerRequestDto;
import com.depromeet.sulsul.domain.beer.dto.BeerResponseDto;
import com.depromeet.sulsul.domain.beer.dto.BeerSearchConditionRequest;
import com.depromeet.sulsul.domain.beer.dto.BeerTypeValue;
import com.depromeet.sulsul.domain.beer.entity.BeerType;
import com.depromeet.sulsul.domain.beer.entity.SortType;
import com.depromeet.sulsul.domain.beer.service.BeerService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/beers")
public class BeerController {

  private final BeerService beerService;

  @GetMapping("")
  public PageableResponseDto<BeerResponseDto> findPageWithFilterRequest(
      @RequestParam("beerId") Long beerId,
      @RequestParam(required = false) List<BeerType> beerTypes,
      @RequestParam(required = false) List<Long> countryIds,
      @RequestParam(required = false) SortType sortType,
      @RequestParam(value = "keyword", required = false) String searchKeyword
  ) {
    Long memberId = 1L; //TODO: (임시 param) 로그인 구현 시 제거
    BeerSearchConditionRequest beerSearchConditionRequest = new BeerSearchConditionRequest(
        beerTypes, countryIds, sortType, searchKeyword);
    return beerService.findPageWithFilterRequest(memberId, beerId, beerSearchConditionRequest);
  }

  @GetMapping("/{beerId}")
  public ResponseDto<BeerDetailResponseDto> findById(@PathVariable("beerId") Long beerId) {
    Long memberId = 1L; //TODO: (임시 param) 로그인 구현 시 제거
    return ResponseDto.from(beerService.findById(memberId, beerId));
  }

  @GetMapping("/types")
  public ResponseDto<List<BeerTypeValue>> findTypes() {
    return ResponseDto.from(beerService.findTypes());
  }

  //TODO: 로그인 기능 개발 후 권한 관련 수정 필요 (관리자용 기능)
  @PostMapping("")
  public ResponseEntity save(@RequestBody BeerRequestDto beerRequestDto) {
    beerService.save(beerRequestDto);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
