package com.depromeet.sulsul.domain.beer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BeerResponsesDto implements Serializable {

  @JsonProperty("beers")
  private List<BeerResponseDto> beerResponseDtos;

  private BeerResponsesDto(List<BeerResponseDto> beerResponseDtos) {
    this.beerResponseDtos = beerResponseDtos;
  }

  public static BeerResponsesDto from(List<BeerResponseDto> beerResponseDtos) {
    return new BeerResponsesDto(beerResponseDtos);
  }
}
