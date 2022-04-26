package com.depromeet.sulsul.domain.beer.dto;

import com.depromeet.sulsul.domain.beer.entity.Beer;
import com.depromeet.sulsul.domain.beer.entity.BeerType;
import com.depromeet.sulsul.domain.country.dto.CountryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BeerDto {

    private CountryDto country;
    private BeerType type;
    private String name;
    private String pictureUrl;
    private String content;
    private Float alcohol;
    private Integer price;
    private Integer volume;

    public BeerDto(CountryDto country, BeerType type, String name, String pictureUrl, String content, Float alcohol, Integer price, Integer volume) {
        this.country = country;
        this.type = type;
        this.name = name;
        this.pictureUrl = pictureUrl;
        this.content = content;
        this.alcohol = alcohol;
        this.price = price;
        this.volume = volume;
    }

    public BeerDto(CountryDto country, Beer beer) {
        this.country = country;
        this.type = beer.getType();
        this.name = beer.getName();
        this.pictureUrl = beer.getPictureUrl();
        this.content = beer.getContent();
        this.alcohol = beer.getAlcohol();
        this.price = beer.getPrice();
        this.volume = beer.getVolume();
    }
}
