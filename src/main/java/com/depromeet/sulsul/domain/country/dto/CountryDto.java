package com.depromeet.sulsul.domain.country.dto;

import com.depromeet.sulsul.domain.continent.dto.ContinentDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryDto {

    private Long id;
    private String name;
    private ContinentDto continent;

    public CountryDto(Long id, String name, ContinentDto continent) {
        this.id = id;
        this.name = name;
        this.continent = continent;
    }
}
