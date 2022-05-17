package com.depromeet.sulsul.domain.record.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecordFindRequest {
    private Long memberId;
    private Long beerId;
    private Long recordId;
}
