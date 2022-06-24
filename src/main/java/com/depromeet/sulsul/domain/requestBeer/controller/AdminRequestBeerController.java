package com.depromeet.sulsul.domain.requestBeer.controller;

import com.depromeet.sulsul.domain.requestBeer.service.RequestBeerService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("guest/admin/api/v1/request-beers")
@RequiredArgsConstructor
@Api(tags = "미등록 맥주 APIs")
public class AdminRequestBeerController {
  private final RequestBeerService requestBeerService;

}
