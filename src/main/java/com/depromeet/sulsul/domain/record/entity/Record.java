package com.depromeet.sulsul.domain.record.entity;

import com.depromeet.sulsul.domain.BaseTimeEntity;
import com.depromeet.sulsul.domain.beer.entity.Beer;
import com.depromeet.sulsul.domain.flavor.entity.Flavor;
import com.depromeet.sulsul.domain.member.entity.Member;
import com.depromeet.sulsul.domain.recordFlavor.entity.RecordFlavor;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Record extends BaseTimeEntity {

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
    private List<RecordFlavor> recordFlavors = new ArrayList<>();

    private String content;
    private Boolean isPublic = false;
    private Integer feel;
    private Integer score;
    private Boolean isDeleted = false;

    public Record(String content, Integer feel, Integer score, Member member, Beer beer) {
        this.content = content;
        this.feel = feel;
        this.score = score;
        this.member = member;
        this.beer = beer;
    }
}
