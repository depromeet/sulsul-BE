package com.depromeet.sulsul.domain.beer.dto;


import com.depromeet.sulsul.domain.beer.entity.BeerType;
import lombok.Getter;

@Getter
public class BeerTypeValue {
    private final String nameEng;
    private final String nameKor;
    private final String description;
    private final String imageUrl;

    public BeerTypeValue(BeerType type) {
        this.nameEng = type.getKey();
        this.nameKor = type.getValue();
        this.description = type.getDescription();
        this.imageUrl = type.getImageUrl();
    }
}
