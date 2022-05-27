package com.depromeet.sulsul.domain.beer.entity;

import com.depromeet.sulsul.common.entity.BaseEntity;
import com.depromeet.sulsul.domain.beer.dto.BeerRequest;
import com.depromeet.sulsul.domain.country.entity.Country;
import com.depromeet.sulsul.domain.record.entity.Record;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.ToString;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(exclude = {"country", "records"})
@EqualsAndHashCode(exclude = {"country", "records"})
public class Beer extends BaseEntity {

  @Id
  @Column(name = "beer_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "country_id")
  private Country country;

  @OneToMany(mappedBy = "beer")
  private List<Record> records = new ArrayList<>();

  @Enumerated(value = EnumType.STRING)
  private BeerType type;
  private String nameKor;
  private String nameEng;
  private String imageUrl;
  private String content;
  private Float alcohol;
  private Integer price;
  private Integer volume;

  public Beer(Country country, BeerRequest beerRequest) {
    this.country = country;
    this.type = beerRequest.getType();
    this.nameKor = beerRequest.getNameKor();
    this.nameEng = beerRequest.getNameEng();
    this.imageUrl = beerRequest.getImageUrl();
    this.content = beerRequest.getContent();
    this.alcohol = beerRequest.getAlcohol();
    this.price = beerRequest.getPrice();
    this.volume = beerRequest.getVolume();
  }
}
