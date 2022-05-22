package com.depromeet.sulsul.common.request;

import com.depromeet.sulsul.domain.beer.entity.BeerType;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Filter {

  @JsonProperty("beerTypes")
  private List<BeerType> beerTypes;
  @JsonProperty("countryIds")
  private List<Long> countryIds;

}
