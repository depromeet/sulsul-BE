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
  private Long memberBeerCount;
  private Long requestbeerCount;

  private String name;

  @Builder
  public MyPageRequestDto(Long beerCount, Long recordCount, Long countryCount,
      Long memberBeerCount,
      Long requestbeerCount) {
    this.beerCount = beerCount;
    this.recordCount = recordCount;
    this.countryCount = countryCount;
    this.memberBeerCount = memberBeerCount;
    this.requestbeerCount = requestbeerCount;
  }

  public static MyPageRequestDto of(Long beerCount, Long recordCount, Long countryCount,
      Long memberBeerCount,
      Long requestbeerCount) {
    return MyPageRequestDto.builder()
        .beerCount(beerCount)
        .recordCount(recordCount)
        .countryCount(countryCount)
        .memberBeerCount(memberBeerCount)
        .requestbeerCount(requestbeerCount)
        .build();
  }
}
