package com.depromeet.sulsul.domain.beer.controller;

import com.depromeet.sulsul.common.dto.EnumValue;
import com.depromeet.sulsul.common.response.dto.PageableResponse;
import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.domain.beer.dto.BeerDetail;
import com.depromeet.sulsul.domain.beer.dto.BeerDto;
import com.depromeet.sulsul.domain.beer.dto.BeerFilterSortRequest;
import com.depromeet.sulsul.domain.beer.dto.BeerRequest;
import com.depromeet.sulsul.domain.beer.entity.BeerType;
import com.depromeet.sulsul.domain.beer.entity.SortType;
import com.depromeet.sulsul.domain.beer.service.BeerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/beers")
public class BeerController {

    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping("")
    public ResponseDto<PageableResponse<BeerDto>> findPageWithFilterRequest(@RequestParam("beerId") Long beerId,
                                                                            @RequestParam(required = false) List<BeerType> beerTypes,
                                                                            @RequestParam(required = false) List<Long> countryIds,
                                                                            @RequestParam(required = false) SortType sortType
                                                            ) {
        Long memberId = 1L; //TODO: (임시 param) 로그인 구현 시 제거
        BeerFilterSortRequest beerFilterSortRequest = new BeerFilterSortRequest(beerTypes, countryIds, sortType);
        return ResponseDto.of(beerService.findPageWithFilterRequest(memberId, beerId, beerFilterSortRequest));
    }

    @GetMapping("/{beerId}")
    public ResponseDto<BeerDetail> findById(@PathVariable("beerId") Long beerId) {
        Long memberId = 1L; //TODO: (임시 param) 로그인 구현 시 제거
        return ResponseDto.of(beerService.findById(memberId, beerId));
    }

    @GetMapping("/types")
    public ResponseDto<List<EnumValue>> findTypes() {
        return ResponseDto.of(beerService.findTypes());
    }

    //TODO: 로그인 기능 개발 후 권한 관련 수정 필요 (관리자용 기능)
    @PostMapping("")
    public ResponseEntity save(@RequestBody BeerRequest beerRequest) {
        beerService.save(beerRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
