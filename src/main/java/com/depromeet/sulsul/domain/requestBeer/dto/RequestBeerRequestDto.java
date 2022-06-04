package com.depromeet.sulsul.domain.requestBeer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestBeerRequestDto {
  private String beerName;
  private String beerImageUrl;

}
