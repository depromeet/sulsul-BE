package com.depromeet.sulsul.domain.record.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecordTicketResponseDto {
  private Long recordId;
  private Integer feel;
  private String startCountryEng;
  private String endCountryEng;
  private String startCountryKor;
  private String endCountryKor;
  private LocalDateTime createdAt;

  private String beerNameKor;
  private String beerNameEng;

  @QueryProjection
  public RecordTicketResponseDto(Long recordId, String beerNameKor, String beerNameEng,
      LocalDateTime createdAt, Integer feel, String startCountryEng, String endCountryEng,
      String startCountryKor, String endCountryKor) {
    this.recordId = recordId;
    this.beerNameKor = beerNameKor;
    this.beerNameEng = beerNameEng;
    this.createdAt = createdAt;
    this.feel = feel;
    this.startCountryEng = startCountryEng;
    this.endCountryEng = endCountryEng;
    this.startCountryKor = startCountryKor;
    this.endCountryKor = endCountryKor;
  }

}
