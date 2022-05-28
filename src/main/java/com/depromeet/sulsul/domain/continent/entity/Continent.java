package com.depromeet.sulsul.domain.continent.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import lombok.ToString;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Continent {

  @Id
  @Column(name = "continent_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
}
