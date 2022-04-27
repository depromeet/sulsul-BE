package com.depromeet.sulsul.domain.country.dto;

import com.depromeet.sulsul.domain.continent.dto.ContinentDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class CountryDto {

    private Long id;
    private String name;
    private ContinentDto continent;

}
