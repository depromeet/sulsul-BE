package com.depromeet.sulsul.domain.record.entity;

import com.depromeet.sulsul.domain.beer.entity.Beer;
import com.depromeet.sulsul.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Record {

    @Id @Column(name = "record_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beer_id")
    private Beer beer;

    private String content;
    private Boolean isPublic;
    private Integer feel;
    private Integer score;
}
