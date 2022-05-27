package com.depromeet.sulsul.domain.beer.controller;

import com.depromeet.sulsul.common.request.ReadRequest;
import com.depromeet.sulsul.common.response.dto.PageableResponseDto;
import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.domain.beer.dto.BeerDetail;
import com.depromeet.sulsul.domain.beer.dto.BeerDto;
import com.depromeet.sulsul.domain.beer.dto.BeerRequest;
import com.depromeet.sulsul.domain.beer.dto.BeerTypeValue;
import com.depromeet.sulsul.domain.beer.service.BeerService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/beers")
public class BeerControllerV2 {

  private final BeerService beerService;

  @PostMapping
  public PageableResponseDto<BeerDto> findPageWithFilterRequest( // TODO: ResponseDto 제거
      @RequestBody(required = false) @Validated ReadRequest readRequest) {
    Long memberId = 1L; //TODO: (임시 param) 로그인 구현 시 제거
    if (readRequest == null) {

      return beerService.findAll(memberId);
    }
    return beerService.findPageWithReadRequest(memberId, readRequest);
  }

  @GetMapping("/{beerId}")
  public ResponseDto<BeerDetail> findById(@PathVariable("beerId") Long beerId) {
    Long memberId = 1L; //TODO: (임시 param) 로그인 구현 시 제거
    return ResponseDto.from(beerService.findById(memberId, beerId));
  }

  @GetMapping("/types")
  public ResponseDto<List<BeerTypeValue>> findTypes() { // TODO: 일급객체로 넘겨야 함.
    return ResponseDto.from(beerService.findTypes());
  }

  //TODO: 로그인 기능 개발 후 권한 관련 수정 필요 (관리자용 기능)
  @PostMapping("/save")
  public ResponseEntity save(@RequestBody BeerRequest beerRequest) {
    beerService.save(beerRequest);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
