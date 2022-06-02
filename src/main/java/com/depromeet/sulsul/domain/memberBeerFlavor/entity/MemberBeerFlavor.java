package com.depromeet.sulsul.domain.memberBeerFlavor.entity;

import com.depromeet.sulsul.domain.beerFlavor.entity.BeerFlavor;
import com.depromeet.sulsul.domain.member.entity.Member;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@ToString(exclude = {"beer", "flavor", "memberBeerFlavors"})
@EqualsAndHashCode(exclude = {"beer", "flavor", "memberBeerFlavors"})
public class MemberBeerFlavor {

  @Id
  @Column(name = "member_beer_flavor_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "beer_flavor_id")
  private BeerFlavor beerFlavor;
}
