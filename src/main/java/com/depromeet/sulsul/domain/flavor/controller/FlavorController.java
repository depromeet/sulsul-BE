package com.depromeet.sulsul.domain.flavor.controller;

import com.depromeet.sulsul.domain.flavor.dto.FlavorResponseDto;
import com.depromeet.sulsul.domain.flavor.entity.Flavor;
import com.depromeet.sulsul.domain.flavor.service.FlavorService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/flavors")
public class FlavorController {

  private final FlavorService flavorService;

  @GetMapping("")
  public List<FlavorResponseDto> test(@RequestParam(required = false) Long beerId){
    return flavorService.findTopFlavors(beerId);
  }

}
