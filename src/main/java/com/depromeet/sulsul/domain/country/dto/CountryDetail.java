package com.depromeet.sulsul.domain.country.dto;

import com.depromeet.sulsul.domain.continent.dto.ContinentDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class CountryDetail {

    private Long id;
    private String name;
    private String imageUrl;
    private ContinentDto continent;

}
