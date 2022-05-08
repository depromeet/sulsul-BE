package com.depromeet.sulsul.domain.record.dto;

import com.depromeet.sulsul.domain.flavor.dto.FlavorDto;
import com.depromeet.sulsul.domain.flavor.entity.Flavor;
import com.depromeet.sulsul.domain.member.dto.MemberRecordDto;
import com.depromeet.sulsul.domain.member.entity.Member;
import com.depromeet.sulsul.domain.recordFlavor.entity.RecordFlavor;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RecordDto {
    private String content;
    private String memberName;
    private String memberProfileurl;
    private Integer feel;
    private Integer score;
    private String flavor;

    @QueryProjection
    public RecordDto(String content, Member member, Integer feel, Integer score, Flavor flavor) {
        this.content = content;
        this.memberName = member.getName();
        this.memberProfileurl = member.getProfileUrl();
        this.feel = feel;
        this.score = score;
        this.flavor = flavor.getContent();
    }

    @QueryProjection
    public RecordDto(String content, Integer feel, Integer score) {
        this.content = content;
        this.feel = feel;
        this.score = score;
    }
}
