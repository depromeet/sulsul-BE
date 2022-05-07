package com.depromeet.sulsul.domain.beer.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SortType {
    REVIEW("리뷰 많은 순"), NAME("이름 순"), ALCOHOL("알코올 도수 순");

    private final String korean;
}
