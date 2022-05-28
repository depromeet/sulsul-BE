package com.depromeet.sulsul.domain.beer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BeerCountResponseDto {

  private Long searchResultCount;
  private Long entireResultCount;
}
