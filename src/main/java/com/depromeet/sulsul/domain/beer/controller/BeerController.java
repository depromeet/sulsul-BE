package com.depromeet.sulsul.domain.beer.controller;

import com.depromeet.sulsul.common.response.dto.PageableResponse;
import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.domain.beer.dto.BeerDto;
import com.depromeet.sulsul.domain.beer.dto.BeerRequest;
import com.depromeet.sulsul.domain.beer.entity.Beer;
import com.depromeet.sulsul.domain.beer.service.BeerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BeerController {

    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping("/v1/beers")
    public ResponseDto<PageableResponse<BeerDto>> findAll(@RequestParam("beerId") Long beerId) {

        return ResponseDto.of(beerService.findAll(beerId));
    }

    //TODO: 로그인 기능 개발 후 권한 관련 수정 필요 (관리자용 기능)
    @PostMapping("/v1/beers")
    public ResponseEntity<Object> save(@RequestBody BeerRequest beerRequest) {
        beerService.save(beerRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
