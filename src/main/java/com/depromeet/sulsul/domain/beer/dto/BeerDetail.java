package com.depromeet.sulsul.domain.beer.dto;

import com.depromeet.sulsul.domain.MemberBeer;
import com.depromeet.sulsul.domain.beer.entity.Beer;
import com.depromeet.sulsul.domain.beer.entity.BeerType;
import com.depromeet.sulsul.domain.continent.dto.ContinentDto;
import com.depromeet.sulsul.domain.country.dto.CountryDetail;
import com.depromeet.sulsul.domain.country.entity.Country;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BeerDetail {

    private Long id;
    private CountryDetail country;
    private BeerType type;
    private String name;
    private String pictureUrl;
    private String content;
    private Float alcohol;
    private Integer price;
    private Integer volume;

    //COMMENT: MEMBER_BEER로 부터 얻는 정보
    private Boolean isLiked = false;

    @QueryProjection
    public BeerDetail(Country country, Beer beer, MemberBeer memberBeer) {
        this.id = beer.getId();
        this.country = new CountryDetail(country.getId(), country.getName(), new ContinentDto(country.getContinent()));
        this.type = beer.getType();
        this.name = beer.getName();
        this.pictureUrl = beer.getPictureUrl();
        this.content = beer.getContent();
        this.alcohol = beer.getAlcohol();
        this.price = beer.getPrice();
        this.volume = beer.getVolume();
        if (memberBeer != null) {
            this.isLiked = true;
        }
    }
}
