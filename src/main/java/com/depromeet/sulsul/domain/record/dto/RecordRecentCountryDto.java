package com.depromeet.sulsul.domain.record.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecordRecentCountryDto {
    private String nameKor;
    private String nameEng;

    @QueryProjection
    public RecordRecentCountryDto(String nameKor, String nameEng) {
        this.nameKor = nameKor;
        this.nameEng = nameEng;
    }
}
