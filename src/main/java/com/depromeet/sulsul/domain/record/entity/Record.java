package com.depromeet.sulsul.domain.record.entity;

import com.depromeet.sulsul.common.entity.BaseEntity;
import com.depromeet.sulsul.domain.beer.entity.Beer;
import com.depromeet.sulsul.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Record extends BaseEntity {

    @Id
    @Column(name = "record_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "record")
    private List<RecordFlavor> recordFlavors = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beer_id")
    private Beer beer;

    private String content;
    private Boolean isPublic;
    private Integer feel;
    private Integer score;
    private Double stampOffsetX;
    private Double stampOffsetY;

    @Builder
    public Record(String content, Boolean isPublic, Integer feel, Integer score, Double stampOffsetX, Double stampOffsetY) {
        this.content = content;
        this.isPublic = isPublic;
        this.feel = feel;
        this.score = score;
        this.stampOffsetX = stampOffsetX;
        this.stampOffsetY = stampOffsetY;
    }

    public void updateBeer(Beer beer) {
        this.beer = beer;
    }
}
