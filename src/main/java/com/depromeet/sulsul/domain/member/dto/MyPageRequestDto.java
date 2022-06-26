package com.depromeet.sulsul.domain.member.dto;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
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
  private Long requestBeerCount;

  @NotEmpty
  private String nickname;

  @Builder
  public MyPageRequestDto(Long beerCount, Long recordCount, Long countryCount,
      Long memberBeerCount,
      Long requestBeerCount,
      String nickname) {
    this.beerCount = beerCount;
    this.recordCount = recordCount;
    this.countryCount = countryCount;
    this.memberBeerCount = memberBeerCount;
    this.requestBeerCount = requestBeerCount;
    this.nickname = nickname;
  }

  public static MyPageRequestDto of(Long beerCount, Long recordCount, Long countryCount,
      Long memberBeerCount,
      Long requestBeerCount) {
    return MyPageRequestDto.builder()
        .beerCount(beerCount)
        .recordCount(recordCount)
        .countryCount(countryCount)
        .memberBeerCount(memberBeerCount)
        .requestBeerCount(requestBeerCount)
        .build();
  }
}
