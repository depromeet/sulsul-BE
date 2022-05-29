package com.depromeet.sulsul.domain.record.entity;

import com.depromeet.sulsul.common.entity.BaseEntity;
import com.depromeet.sulsul.domain.beer.entity.Beer;
import com.depromeet.sulsul.domain.member.entity.Member;
import com.depromeet.sulsul.domain.record.dto.RecordRequestDto;
import com.depromeet.sulsul.domain.recordFlavor.entity.RecordFlavor;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@ToString(exclude = {"member", "beer", "recordFlavors"})
@EqualsAndHashCode(exclude = {"member", "beer", "recordFlavors"})
public class Record extends BaseEntity {

  @Id
  @Column(name = "record_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "beer_id")
  private Beer beer;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "record")
  private final List<RecordFlavor> recordFlavors = new ArrayList<>();

  private String content;
  private Boolean isPublic;
  private Integer feel;
  private Integer score;
  @Builder
  public Record(String content, Boolean isPublic, Integer feel, Integer score) {
    this.content = content;
    this.isPublic = isPublic;
    this.feel = feel;
    this.score = score;
  }
  public void updateBeer(Beer beer) {
    this.beer = beer;
  }

  public Record(Member member, Beer beer, RecordRequestDto recordRequestDto) {
    this.member = member;
    this.beer = beer;
    this.content = recordRequestDto.getContent();
    this.isPublic = recordRequestDto.getIsPublic();
    this.feel = recordRequestDto.getFeel();
  }
}
