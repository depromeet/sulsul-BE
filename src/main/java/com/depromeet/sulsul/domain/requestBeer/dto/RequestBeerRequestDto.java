package com.depromeet.sulsul.domain.requestBeer.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestBeerRequestDto {
  private String beerName;
  private List<String> beerImageUrls = new ArrayList<>();
}
