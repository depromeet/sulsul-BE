package com.depromeet.sulsul.domain.beerFlavor.entity;

import com.depromeet.sulsul.domain.beer.entity.Beer;
import com.depromeet.sulsul.domain.flavor.entity.Flavor;
import com.depromeet.sulsul.domain.memberBeerFlavor.entity.MemberBeerFlavor;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
