package com.depromeet.sulsul.domain.record.dto;

import com.depromeet.sulsul.domain.flavor.dto.FlavorDto;
import com.depromeet.sulsul.domain.flavor.entity.Flavor;
import com.depromeet.sulsul.domain.member.dto.MemberRecordDto;
import com.depromeet.sulsul.domain.member.entity.Member;
import com.depromeet.sulsul.domain.recordFlavor.entity.RecordFlavor;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RecordDto {
    private String content;
    private Integer feel;
    private Integer score;

    private MemberRecordDto memberRecordDto;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    private List<Flavor> flavor;

    @QueryProjection
    public RecordDto(String content, MemberRecordDto memberRecordDto, Integer feel, Integer score, List<Flavor> flavor
                    , LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.content = content;
        this.feel = feel;
        this.score = score;
        this.flavor = flavor;

        this.memberRecordDto = memberRecordDto;

        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    @QueryProjection
    public RecordDto(String content, Integer feel, Integer score) {
        this.content = content;
        this.feel = feel;
        this.score = score;
    }
}
