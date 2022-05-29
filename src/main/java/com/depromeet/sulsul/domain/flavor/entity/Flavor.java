package com.depromeet.sulsul.domain.flavor.entity;

import com.depromeet.sulsul.common.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@ToString
@EqualsAndHashCode
public class Flavor extends BaseEntity {

  @Id
  @Column(name = "flavor_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String content;
}
