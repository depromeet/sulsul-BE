package com.depromeet.sulsul.domain.continent.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Continent {

  @Id
  @Column(name = "continent_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
}
