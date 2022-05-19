package com.depromeet.sulsul.domain.records.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecordsFindRequest {
    private Long memberId;
    private Long beerId;
    private Long recordId;
}
