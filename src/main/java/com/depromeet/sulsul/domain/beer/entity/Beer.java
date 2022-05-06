package com.depromeet.sulsul.domain.beer.entity;

import com.depromeet.sulsul.domain.beer.dto.BeerDto;
import com.depromeet.sulsul.domain.beer.dto.BeerRequest;
import com.depromeet.sulsul.domain.beer.dto.BeerUpdateRequest;
import com.depromeet.sulsul.domain.country.entity.Country;
import com.depromeet.sulsul.domain.review.entity.Review;
import lombok.AccessLevel;
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
    private Boolean isDeleted = false;

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

    public void update(Country country, BeerUpdateRequest beerUpdateRequest){
        this.country = country;
        this.type = beerUpdateRequest.getType();
        this.name = beerUpdateRequest.getName();
        this.pictureUrl = beerUpdateRequest.getPictureUrl();
        this.content = beerUpdateRequest.getContent();
        this.alcohol = beerUpdateRequest.getAlcohol();
        this.price = beerUpdateRequest.getPrice();
        this.volume = beerUpdateRequest.getVolume();
    }

    public void deleteBeer(){
        this.isDeleted = true;
    }
}
