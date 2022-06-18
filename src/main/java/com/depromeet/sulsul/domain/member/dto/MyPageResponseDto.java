package com.depromeet.sulsul.domain.member.dto;

import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MyPageResponseDto  implements Serializable {

  private Long beerCount;
  private Long recordCount;
  private Long countryCount;
  private Long memberBeerCount;
  private Long requestBeerCount;

  private String nickname;
  private String email;
  private Long remainRecord;

  @Builder
  public MyPageResponseDto(Long beerCount, Long recordCount, Long countryCount,
      Long memberBeerCount,
      Long requestBeerCount,
      String nickname,
      String email,
      Long remainRecord) {
    this.beerCount = beerCount;
    this.recordCount = recordCount;
    this.countryCount = countryCount;
    this.memberBeerCount = memberBeerCount;
    this.requestBeerCount = requestBeerCount;
    this.nickname = nickname;
    this.email = email;
    this.remainRecord = remainRecord;
  }

  public static MyPageResponseDto of(Long beerCount, Long recordCount, Long countryCount,
      Long memberBeerCount,
      Long requestBeerCount,
      String nickname,
      String email,
      Long remainRecord) {
    return MyPageResponseDto.builder()
        .beerCount(beerCount)
        .recordCount(recordCount)
        .countryCount(countryCount)
        .memberBeerCount(memberBeerCount)
        .requestBeerCount(requestBeerCount)
        .nickname(nickname)
        .email(email)
        .remainRecord(remainRecord)
        .build();
  }
}
