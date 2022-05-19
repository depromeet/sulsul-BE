package com.depromeet.sulsul.domain.records.dto;

import com.depromeet.sulsul.domain.flavor.entity.Flavor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecordsRequest {
    private String content;
    private Long beerId;
    private List<Flavor> Flavors = new ArrayList<>();
    private Boolean isPublic;
    private Integer feel;
    private Integer score;
}