package com.depromeet.sulsul.domain.record.dto;

import com.depromeet.sulsul.domain.flavor.dto.FlavorDto;
import com.depromeet.sulsul.domain.member.dto.MemberRecordsDto;
import com.querydsl.core.annotations.QueryProjection;
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
    private MemberRecordsDto memberRecordsDto;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<FlavorDto> flavorDtos = new ArrayList<>();

    @QueryProjection
    public RecordDto(String content, MemberRecordsDto memberRecordsDto, Integer feel, List<FlavorDto> flavorDtos
            , LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.content = content;
        this.feel = feel;
        this.flavorDtos = flavorDtos;
        this.memberRecordsDto = memberRecordsDto;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

}
