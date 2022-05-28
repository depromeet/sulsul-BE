package com.depromeet.sulsul.domain.beerFlavor.entity;

import com.depromeet.sulsul.domain.beer.entity.Beer;
import com.depromeet.sulsul.domain.flavor.entity.Flavor;
import com.depromeet.sulsul.domain.memberBeerFlavor.entity.MemberBeerFlavor;
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
@ToString(exclude = {"beer", "flavor", "memberBeerFlavors"})
@EqualsAndHashCode(exclude = {"beer", "flavor", "memberBeerFlavors"})
public class BeerFlavor {

  @Id
  @Column(name = "beer_flavor_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "beer_id")
  private Beer beer;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "flavor_id")
  private Flavor flavor;

  @OneToMany(mappedBy = "beerFlavor")
  private List<MemberBeerFlavor> memberBeerFlavors = new ArrayList<>();
}
