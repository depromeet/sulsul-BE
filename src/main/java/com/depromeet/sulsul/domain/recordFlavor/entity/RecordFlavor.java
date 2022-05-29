package com.depromeet.sulsul.domain.recordFlavor.entity;

import com.depromeet.sulsul.common.entity.BaseEntity;
import com.depromeet.sulsul.domain.flavor.entity.Flavor;
import com.depromeet.sulsul.domain.record.entity.Record;
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
@ToString(exclude = {"record", "flavor"})
@EqualsAndHashCode(exclude = {"record", "flavor"})
public class RecordFlavor extends BaseEntity {

  @Id
  @Column(name = "beer_flavor_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "record_id")
  private Record record;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "flavor_id")
  private Flavor flavor;

  public RecordFlavor(Record record, Flavor flavor) {
    this.record = record;
    this.flavor = flavor;
  }
}
