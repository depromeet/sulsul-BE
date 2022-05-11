package com.depromeet.sulsul.domain.beerFlavor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BeerFlavorRequest {
    private Long beerId;
    private List<Long> flavorIds = new ArrayList<>();
}
