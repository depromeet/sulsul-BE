package com.depromeet.sulsul.common.response.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageableResponse<T> {

    private Boolean hasNext;
    private List<T> contents;

    public PageableResponse(Boolean hasNext, List<T> contents) {
        this.hasNext = hasNext;
        this.contents = contents;
    }
}
