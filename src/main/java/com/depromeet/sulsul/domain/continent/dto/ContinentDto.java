package com.depromeet.sulsul.domain.continent.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContinentDto {

    private Long id;
    private String name;

    public ContinentDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
