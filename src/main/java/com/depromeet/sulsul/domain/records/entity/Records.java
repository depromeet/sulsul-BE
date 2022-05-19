package com.depromeet.sulsul.domain.records.entity;

import com.depromeet.sulsul.common.entity.BaseEntity;
import com.depromeet.sulsul.domain.beer.entity.Beer;
import com.depromeet.sulsul.domain.member.entity.Member;
import com.depromeet.sulsul.domain.records.dto.RecordsRequest;
import com.depromeet.sulsul.domain.recordsFlavor.entity.RecordsFlavor;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Records extends BaseEntity {

    @Id @Column(name = "record_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beer_id")
    private Beer beer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "records")
    private List<RecordsFlavor> recordsFlavors = new ArrayList<>();

    private String content;
    private Boolean isPublic;
    private Integer feel;
    private Integer score;

    public Records(Member member, Beer beer, RecordsRequest recordsRequest) {
        this.member = member;
        this.beer = beer;
        this.content = recordsRequest.getContent();
        this.isPublic = recordsRequest.getIsPublic();
        this.feel = recordsRequest.getFeel();
        this.score = recordsRequest.getScore();
    }
}
