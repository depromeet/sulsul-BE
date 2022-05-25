package com.depromeet.sulsul.domain.record.entity;

import com.depromeet.sulsul.common.entity.BaseEntity;
import com.depromeet.sulsul.domain.beer.entity.Beer;
import com.depromeet.sulsul.domain.member.entity.Member;
import com.depromeet.sulsul.domain.record.dto.RecordRequest;
import com.depromeet.sulsul.domain.recordFlavor.entity.RecordFlavor;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Record extends BaseEntity {

    @Id @Column(name = "record_id")
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

    public Record(Member member, Beer beer, RecordRequest recordRequest) {
        this.member = member;
        this.beer = beer;
        this.content = recordRequest.getContent();
        this.isPublic = recordRequest.getIsPublic();
        this.feel = recordRequest.getFeel();
    }
}
