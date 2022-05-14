package com.depromeet.sulsul.domain.review.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewUpdateRequest {
    private Long id;
    private String content;
    private Long memberId;
    private Long beerId;
}
