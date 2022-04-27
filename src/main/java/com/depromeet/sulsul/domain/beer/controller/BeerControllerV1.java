package com.depromeet.sulsul.domain.beer.controller;

import com.depromeet.sulsul.common.response.dto.PageableResponse;
import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.domain.beer.dto.BeerDto;
import com.depromeet.sulsul.domain.beer.dto.BeerRequest;
import com.depromeet.sulsul.domain.beer.service.BeerService;
import com.depromeet.sulsul.domain.beer.service.BeerServiceImplV1;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class BeerControllerV1 {

    private final BeerService beerService;

    public BeerControllerV1(BeerServiceImplV1 beerService) {
        this.beerService = beerService;
    }

    @GetMapping("/beers")
    public ResponseDto<PageableResponse<BeerDto>> findAll(@RequestParam("beerId") Long beerId,
                                                          @RequestParam("memberId") Long memberId //TODO: (임시 param) 로그인 구현 시 제거
    ) {

        return ResponseDto.of(beerService.findAll(memberId, beerId));
    }

    //TODO: 로그인 기능 개발 후 권한 관련 수정 필요 (관리자용 기능)
    @PostMapping("/beers")
    public ResponseEntity<Object> save(@RequestBody BeerRequest beerRequest) {
        beerService.save(beerRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
