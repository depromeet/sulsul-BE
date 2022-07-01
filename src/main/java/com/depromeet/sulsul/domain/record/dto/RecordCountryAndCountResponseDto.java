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

  public RecordCountryAndCountResponseDto(RecordRecentCountryDto recordRecentCountryDto, Long recordCountByRecentCountry) {
    this.nameKor = recordRecentCountryDto.getNameKor();
    this.nameEng = recordRecentCountryDto.getNameEng();
    this.count = recordCountByRecentCountry;
  }
}
