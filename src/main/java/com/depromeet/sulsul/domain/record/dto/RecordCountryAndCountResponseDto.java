package com.depromeet.sulsul.domain.record.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecordCountryAndCountResponseDto {
  String country;
  Long count;

  @QueryProjection
  public RecordCountryAndCountResponseDto(String country, Long count) {
    this.country = country;
    this.count = count;
  }
}
