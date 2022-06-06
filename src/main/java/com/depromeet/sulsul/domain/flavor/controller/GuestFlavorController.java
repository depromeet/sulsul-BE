package com.depromeet.sulsul.domain.flavor.controller;

import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.domain.flavor.dto.FlavorResponseDto;
import com.depromeet.sulsul.domain.flavor.service.FlavorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(tags = "맛 조회 APIs (게스트용)")
@RequestMapping("/guest/api/v1/flavors")
public class GuestFlavorController {

  private final FlavorService flavorService;

  @ApiOperation(value = "해당 맥주의 최상위 맛 3개 조회 API")
  @GetMapping("/{beerId}")
  public ResponseDto<List<FlavorResponseDto>> findTopFlavors(@PathVariable("beerId") Long beerId){
    return ResponseDto.from(flavorService.findTopFlavors(beerId));
  }
}
