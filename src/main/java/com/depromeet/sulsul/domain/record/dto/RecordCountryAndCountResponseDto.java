package com.depromeet.sulsul.domain.record.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class RecordCountryAndCountResponseDto {
  private String nameKor;
  private String nameEng;
  private Long count;

  @QueryProjection
  public RecordCountryAndCountResponseDto(String nameKor, String nameEng, Long count) {
    this.nameKor = nameKor;
    this.nameEng = nameEng;
    this.count = count;
  }
}
