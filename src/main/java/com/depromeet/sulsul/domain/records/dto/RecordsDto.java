package com.depromeet.sulsul.domain.records.dto;

import com.depromeet.sulsul.domain.flavor.dto.FlavorDto;
import com.depromeet.sulsul.domain.flavor.entity.Flavor;
import com.depromeet.sulsul.domain.member.dto.MemberRecordsDto;
import com.depromeet.sulsul.domain.member.entity.Member;
import com.depromeet.sulsul.domain.recordsFlavor.entity.RecordsFlavor;
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
public class RecordsDto {
    private String content;
    private Integer feel;
    private Integer score;

    private MemberRecordsDto memberRecordsDto;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    private List<Flavor> flavor = new ArrayList<>();

    @QueryProjection
    public RecordsDto(String content, MemberRecordsDto memberRecordsDto, Integer feel, Integer score, List<Flavor> flavor
            , LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.content = content;
        this.feel = feel;
        this.score = score;
        this.flavor = flavor;

        this.memberRecordsDto = memberRecordsDto;

        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    @QueryProjection
    public RecordsDto(String content, Integer feel, Integer score) {
        this.content = content;
        this.feel = feel;
        this.score = score;
    }
}
