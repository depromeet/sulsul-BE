package com.depromeet.sulsul.domain.beer.dto;

import com.depromeet.sulsul.domain.beer.entity.Beer;
import com.depromeet.sulsul.domain.continent.dto.ContinentDto;
import com.depromeet.sulsul.domain.country.dto.CountryDetail;
import com.depromeet.sulsul.domain.country.dto.CountryNameDto;
import com.depromeet.sulsul.domain.country.entity.Country;
import com.depromeet.sulsul.domain.memberBeer.entity.MemberBeer;
import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BeerDetailResponseDto {

  private Long id;
  private CountryDetail country;
  private BeerTypeValue type;
  private String nameKor;
  private String nameEng;
  private CountryNameDto startCountry;
  private CountryNameDto endCountry;
  private String imageUrl;
  private String content;
  private Float alcohol;
  private Integer price;
  private Integer volume;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  //COMMENT: MEMBER_BEER로 부터 얻는 정보
  private Boolean isLiked = false;

  @QueryProjection
  public BeerDetailResponseDto(Country country, Beer beer, MemberBeer memberBeer,
      CountryNameDto startCountry) {
    this.id = beer.getId();
    this.country = new CountryDetail(country.getId(),
        country.getNameKor(), country.getNameEng(), country.getImageUrl(),
        country.getBackgroundImageUrl(),
        new ContinentDto(country.getContinent()));
    this.type = new BeerTypeValue(beer.getType());
    this.nameKor = beer.getNameKor();
    this.nameEng = beer.getNameEng();
    this.startCountry = startCountry;    // TODO: 구현 중 (사용자가 작성한 마지막 기록의 도착지)
    this.endCountry = new CountryNameDto(country.getNameKor(), country.getNameEng());
    this.imageUrl = beer.getImageUrl();
    this.content = beer.getContent();
    this.alcohol = beer.getAlcohol();
    this.price = beer.getPrice();
    this.volume = beer.getVolume();
    this.createdAt = beer.getCreatedAt();
    this.updatedAt = beer.getUpdatedAt();
    if (memberBeer != null) {
      this.isLiked = true;
    }
  }
}
