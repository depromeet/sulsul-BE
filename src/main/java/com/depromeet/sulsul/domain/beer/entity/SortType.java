package com.depromeet.sulsul.domain.beer.entity;

import com.depromeet.sulsul.common.dto.EnumModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SortType implements EnumModel {
    REVIEW("리뷰 많은 순"), NAME("이름 순"), ALCOHOL("알코올 도수 순");

    private final String korean;

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return korean;
    }
}
