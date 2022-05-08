package com.depromeet.sulsul.domain.beer.entity;

import com.depromeet.sulsul.domain.beer.dto.BeerDto;
import com.depromeet.sulsul.domain.beer.dto.BeerRequest;
import com.depromeet.sulsul.domain.country.entity.Country;
import com.depromeet.sulsul.domain.review.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@AllArgsConstructor
public class Beer {

    @Id
    @Column(name = "beer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(mappedBy = "beer")
    private List<Review> reviews = new ArrayList<>();

    private BeerType type;
    private String name;
    private String pictureUrl;
    private String content;
    private Float alcohol;
    private Integer price;
    private Integer volume;

    public Beer(Country country, BeerDto beerDto) {
        this.country = country;
        this.type = beerDto.getType();
        this.name = beerDto.getName();
        this.pictureUrl = beerDto.getPictureUrl();
        this.content = beerDto.getContent();
        this.alcohol = beerDto.getAlcohol();
        this.price = beerDto.getPrice();
        this.volume = beerDto.getVolume();
    }

    public Beer(Country country, BeerRequest beerRequest) {
        this.country = country;
        this.type = beerRequest.getType();
        this.name = beerRequest.getName();
        this.pictureUrl = beerRequest.getPictureUrl();
        this.content = beerRequest.getContent();
        this.alcohol = beerRequest.getAlcohol();
        this.price = beerRequest.getPrice();
        this.volume = beerRequest.getVolume();
    }


    public Beer(Long id, Country country, BeerType type, String name, String pictureUrl, String content, Float alcohol, Integer price, Integer volume) {
        this.id = id;
        this.country = country;
        this.reviews = reviews;
        this.type = type;
        this.name = name;
        this.pictureUrl = pictureUrl;
        this.content = content;
        this.alcohol = alcohol;
        this.price = price;
        this.volume = volume;
    }

}
