package com.depromeet.sulsul.domain.country.dto;

import com.depromeet.sulsul.domain.country.entity.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CountryDto {

  private final Long id;
  private final String nameKor;
  private final String nameEng;
  private final String imageUrl;
  private final String backgroundImageUrl;

  public CountryDto(Country country) {
    this.id = country.getId();
    this.nameKor = country.getNameKor();
    this.nameEng = country.getNameEng();
    this.imageUrl = country.getImageUrl();
    this.backgroundImageUrl = country.getBackgroundImageUrl();
  }
}
