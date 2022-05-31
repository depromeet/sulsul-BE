package com.depromeet.sulsul.domain.member.dto;

import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MyPageRequestDto implements Serializable {

  private Long beerCount;
  private Long recordCount;
  private Long countryCount;
  private Long unregisteredBeerCount;
  private Long favoriteBeerCount;

  private String name;

  @Builder
  public MyPageRequestDto(Long beerCount, Long recordCount, Long countryCount, Long unregisteredBeerCount,
      Long favoriteBeerCount) {
    this.beerCount = beerCount;
    this.recordCount = recordCount;
    this.countryCount = countryCount;
    this.unregisteredBeerCount = unregisteredBeerCount;
    this.favoriteBeerCount = favoriteBeerCount;
  }

  public static MyPageRequestDto of(Long beerCount, Long recordCount, Long countryCount, Long unregisteredBeerCount,
      Long favoriteBeerCount) {
    return MyPageRequestDto.builder()
        .beerCount(beerCount)
        .recordCount(recordCount)
        .countryCount(countryCount)
        .unregisteredBeerCount(unregisteredBeerCount)
        .favoriteBeerCount(favoriteBeerCount)
        .build();
  }
}
