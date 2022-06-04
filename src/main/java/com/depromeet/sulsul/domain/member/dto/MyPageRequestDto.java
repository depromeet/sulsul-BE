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
  private Long memberBeerService;
  private Long requestBeerService;

  private String name;

  @Builder
  public MyPageRequestDto(Long beerCount, Long recordCount, Long countryCount,
      Long memberBeerService,
      Long requestBeerService) {
    this.beerCount = beerCount;
    this.recordCount = recordCount;
    this.countryCount = countryCount;
    this.memberBeerService = memberBeerService;
    this.requestBeerService = requestBeerService;
  }

  public static MyPageRequestDto of(Long beerCount, Long recordCount, Long countryCount,
      Long memberBeerService,
      Long requestBeerService) {
    return MyPageRequestDto.builder()
        .beerCount(beerCount)
        .recordCount(recordCount)
        .countryCount(countryCount)
        .memberBeerService(memberBeerService)
        .requestBeerService(requestBeerService)
        .build();
  }
}
