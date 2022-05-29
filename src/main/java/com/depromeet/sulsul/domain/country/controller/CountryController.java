package com.depromeet.sulsul.domain.country.controller;

import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.domain.country.dto.CountryDto;
import com.depromeet.sulsul.domain.country.service.CountryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(tags = "국가 APIs (version 1)")
@RequestMapping("/api/v1/countries")
public class CountryController {

  private final CountryService countryService;

  @GetMapping("")
  @ApiOperation(value = "국가 전체 조회 API")
  public ResponseDto<List<CountryDto>> findAll(
      @RequestParam(value = "continentId", required = false) Long continentId) {
    return ResponseDto.from(countryService.findAllByContinentId(continentId));
  }
}
