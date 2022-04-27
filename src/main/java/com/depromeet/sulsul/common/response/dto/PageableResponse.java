package com.depromeet.sulsul.common.response.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageableResponse<T> {

    private Boolean hasNext = false;
    private List<T> contents;

}