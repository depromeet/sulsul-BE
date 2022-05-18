package com.depromeet.sulsul.domain.continent.dto;

import com.depromeet.sulsul.domain.continent.entity.Continent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class ContinentDto {

    private final Long id;
    private final String name;

    public ContinentDto(Continent continent) {
        this.id = continent.getId();
        this.name = continent.getName();
    }

}
