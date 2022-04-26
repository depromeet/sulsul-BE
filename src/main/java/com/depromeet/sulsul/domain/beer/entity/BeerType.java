package com.depromeet.sulsul.domain.beer.entity;

import lombok.Getter;

@Getter
public enum BeerType {
    //COMMENT: 임시 종류
    LIGHT_ALE("라이트 에일"),
    IPA("인디아 페일 에일"),
    PALE_ALE("페일 에일"),
    BROWN_ALE("브라운 에일"),
    LARGER("라거"),
    PORTER("포터"),
    WEIZEN("바이젠"),
    Pilsner("필스너");

    private String korean;

    BeerType(String korean) {
        this.korean = korean;
    }
}
