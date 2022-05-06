package com.depromeet.sulsul.domain.beer.dto;

import com.depromeet.sulsul.domain.beer.entity.BeerType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BeerUpdateRequest {

    private static final long serialVersionUID = -2120618888041262425L;

    private Long id;
    private String content;
}
