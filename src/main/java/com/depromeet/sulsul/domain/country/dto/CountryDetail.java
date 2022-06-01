package com.depromeet.sulsul.domain.country.dto;

import com.depromeet.sulsul.domain.continent.dto.ContinentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CountryDetail {

  private final Long id;
  private final String nameKor;
  private final String nameEng;
  private final String imageUrl;
  private final String backgroundImageUrl;
  private final ContinentDto continent;

}
