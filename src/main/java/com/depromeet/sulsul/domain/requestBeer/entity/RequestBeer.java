package com.depromeet.sulsul.domain.requestBeer.entity;

import com.depromeet.sulsul.common.entity.BaseEntity;
import com.depromeet.sulsul.domain.member.entity.Member;
import com.depromeet.sulsul.domain.requestBeer.dto.RequestBeerRequestDto;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(exclude = {"member"})
@EqualsAndHashCode(exclude = {"member"})
public class RequestBeer extends BaseEntity {
  @Id
  @Column(name = "request_beer_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long requestBeerId;

  @ManyToOne(fetch = FetchType.LAZY)
  private Member member;

  private String beerName;
  private String beerImageUrl;

  private LocalDateTime requestCompletedAt;

  private String requestRejectionReason;

  @Enumerated(EnumType.STRING)
  private RequestBeerStatus status = RequestBeerStatus.PENDING; // default waiting..

  public RequestBeer(RequestBeerRequestDto requestBeerRequestDto, Member member){
    this.beerName = requestBeerRequestDto.getBeerName();
    this.beerImageUrl = requestBeerRequestDto.getBeerImageUrl();
    this.member = member;
  }

}
