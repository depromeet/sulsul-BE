package com.depromeet.sulsul.domain.country.controller;

import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.domain.country.dto.CountryDto;
import com.depromeet.sulsul.domain.country.service.CountryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("")
    public ResponseDto<List<CountryDto>> findAll(@RequestParam(value = "continentId", required = false) Long continentId) {
        return ResponseDto.of(countryService.findAllByContinentId(continentId));
    }
}
