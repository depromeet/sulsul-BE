package com.depromeet.sulsul.domain.memberBeerFlavor.entity;

import com.depromeet.sulsul.domain.beerFlavor.entity.BeerFlavor;
import com.depromeet.sulsul.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
