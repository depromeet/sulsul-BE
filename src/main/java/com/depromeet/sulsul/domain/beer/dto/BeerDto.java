package com.depromeet.sulsul.domain.beer.dto;

import com.depromeet.sulsul.domain.MemberBeer;
import com.depromeet.sulsul.domain.beer.entity.Beer;
import com.depromeet.sulsul.domain.continent.dto.ContinentDto;
import com.depromeet.sulsul.domain.country.dto.CountryDetail;
import com.depromeet.sulsul.domain.country.entity.Country;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BeerDto {

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

    //COMMENT: RECORD로 부터 얻는 정보
    private Integer feel;

    //COMMENT: MEMBER_BEER로 부터 얻는 정보
    private Boolean isLiked = false;

    @QueryProjection
    public BeerDto(Country country, Beer beer, Integer feel, MemberBeer memberBeer) {
        this.id = beer.getId();
        this.country = new CountryDetail(country.getId(), country.getNameKor(), country.getNameEng(),
                country.getImageUrl(), new ContinentDto(country.getContinent()));
        this.type = new BeerTypeValue(beer.getType());
        this.nameKor = beer.getNameKor();
        this.nameEng = beer.getNameEng();
        this.imageUrl = beer.getImageUrl();
        this.content = beer.getContent();
        this.alcohol = beer.getAlcohol();
        this.price = beer.getPrice();
        this.volume = beer.getVolume();
        this.feel = feel;
        if (memberBeer != null) {
            this.isLiked = true;
        }
    }
}
