package com.depromeet.sulsul.domain.beer.entity;

import com.depromeet.sulsul.common.entity.BaseEntity;
import com.depromeet.sulsul.domain.beer.dto.BeerRequestDto;
import com.depromeet.sulsul.domain.country.entity.Country;
import com.depromeet.sulsul.domain.record.entity.Record;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

  public Beer(Country country, BeerRequestDto beerRequestDto) {
    this.country = country;
    this.type = beerRequestDto.getType();
    this.nameKor = beerRequestDto.getNameKor();
    this.nameEng = beerRequestDto.getNameEng();
    this.imageUrl = beerRequestDto.getImageUrl();
    this.content = beerRequestDto.getContent();
    this.alcohol = beerRequestDto.getAlcohol();
    this.price = beerRequestDto.getPrice();
    this.volume = beerRequestDto.getVolume();
  }
}
