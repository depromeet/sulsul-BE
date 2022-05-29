package com.depromeet.sulsul.domain.flavor.entity;

import com.depromeet.sulsul.common.entity.BaseEntity;
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
public class Flavor extends BaseEntity {

  @Id
  @Column(name = "flavor_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String content;
}
