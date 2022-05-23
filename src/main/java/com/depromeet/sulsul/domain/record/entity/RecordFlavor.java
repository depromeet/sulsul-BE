package com.depromeet.sulsul.domain.record.entity;

import com.depromeet.sulsul.common.entity.BaseEntity;
import com.depromeet.sulsul.domain.flavor.entity.Flavor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecordFlavor extends BaseEntity {

  @Id
  @Column(name = "record_flavor_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "record_id")
  private Record record;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "flavor_id")
  private Flavor flavor;

  @Builder
  public RecordFlavor(Record record, Flavor flavor) {
    this.record = record;
    this.flavor = flavor;
  }

  public static RecordFlavor of(Record record, Flavor flavor) {
    return RecordFlavor.builder()
        .record(record)
        .flavor(flavor)
        .build();
  }
}