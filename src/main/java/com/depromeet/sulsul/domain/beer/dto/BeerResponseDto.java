package com.depromeet.sulsul.domain.beer.dto;

import com.depromeet.sulsul.domain.beer.entity.Beer;
import com.depromeet.sulsul.domain.continent.dto.ContinentDto;
import com.depromeet.sulsul.domain.country.dto.CountryDetail;
import com.depromeet.sulsul.domain.country.entity.Country;
import com.depromeet.sulsul.domain.memberBeer.entity.MemberBeer;
import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BeerResponseDto {

  private Long id;
  private CountryDetail country;
  private BeerTypeValue type;
  private String nameKor;
  private String nameEng;
  private String imageUrl;
  private String content;
  private Float alcohol;
  private Integer price;
  private Integer volume;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  //COMMENT: RECORD로 부터 얻는 정보
  private Integer feel;

  //COMMENT: MEMBER_BEER로 부터 얻는 정보
  private Boolean isLiked = false;

  @QueryProjection
  public BeerResponseDto(Country country, Beer beer, Integer feel, MemberBeer memberBeer) {
    this.id = beer.getId();
    this.country = new CountryDetail(country.getId(), country.getNameKor(), country.getNameEng(),
        country.getImageUrl(), country.getBackgroundImageUrl(),
        new ContinentDto(country.getContinent()));
    this.type = new BeerTypeValue(beer.getType());
    this.nameKor = beer.getNameKor();
    this.nameEng = beer.getNameEng();
    this.imageUrl = beer.getImageUrl();
    this.content = beer.getContent();
    this.alcohol = beer.getAlcohol();
    this.price = beer.getPrice();
    this.volume = beer.getVolume();
    this.createdAt = beer.getCreatedAt();
    this.updatedAt = beer.getUpdatedAt();
    this.feel = feel;
    if (memberBeer != null) {
      this.isLiked = true;
    }
  }
}
