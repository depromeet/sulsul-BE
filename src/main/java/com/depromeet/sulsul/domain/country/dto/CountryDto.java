package com.depromeet.sulsul.domain.country.dto;

import com.depromeet.sulsul.domain.country.entity.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CountryDto {

    private Long id;
    private String name;

    public CountryDto(Country country) {
        this.id = country.getId();
        this.name = country.getName();
    }
}
