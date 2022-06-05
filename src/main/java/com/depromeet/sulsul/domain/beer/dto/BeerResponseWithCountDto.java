package com.depromeet.sulsul.domain.beer.dto;

import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BeerResponseWithCountDto {

  private long resultCount;
  private List<BeerResponseDto> beerResponseDtos;

  public long getResultCount() {
    return resultCount;
  }

  public List<BeerResponseDto> getBeerResponseDtos() {
    return beerResponseDtos;
  }

  public BeerResponseWithCountDto(long resultCount,
      List<BeerResponseDto> beerResponseDtos) {
    this.resultCount = resultCount;
    this.beerResponseDtos = beerResponseDtos;
  }
}
