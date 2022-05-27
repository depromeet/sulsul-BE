package com.depromeet.sulsul.domain.country.entity;

import com.depromeet.sulsul.domain.continent.entity.Continent;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(exclude = "continent")
@EqualsAndHashCode(exclude = "continent")
public class Country {

  @Id
  @Column(name = "country_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "continent_id")
  private Continent continent;

  private String nameKor;
  private String nameEng;
  private String imageUrl;
}
