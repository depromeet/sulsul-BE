package com.depromeet.sulsul.domain.review.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewDeleteRequest {
    private Long id;
    private Long memberId;
    private Long beerId;
}
