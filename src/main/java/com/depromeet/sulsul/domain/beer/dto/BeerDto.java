package com.depromeet.sulsul.domain.beer.dto;

import com.depromeet.sulsul.domain.MemberBeer;
import com.depromeet.sulsul.domain.beer.entity.Beer;
import com.depromeet.sulsul.domain.beer.entity.BeerType;
import com.depromeet.sulsul.domain.continent.dto.ContinentDto;
import com.depromeet.sulsul.domain.country.dto.CountryDto;
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
    private CountryDto country;
    private BeerType type;
    private String name;
    private String pictureUrl;
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
        this.country = new CountryDto(country.getId(), country.getName(), new ContinentDto(country.getContinent()));
        this.type = beer.getType();
        this.name = beer.getName();
        this.pictureUrl = beer.getPictureUrl();
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
