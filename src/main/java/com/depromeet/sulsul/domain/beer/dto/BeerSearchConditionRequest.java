package com.depromeet.sulsul.domain.beer.dto;

import com.depromeet.sulsul.domain.beer.entity.BeerType;
import com.depromeet.sulsul.domain.beer.entity.SortType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

// TODO: will be deprecated.
@Getter
@AllArgsConstructor
public final class BeerSearchConditionRequest {

  private List<BeerType> beerTypes;
  private List<Long> countryIds;
  private SortType sortType;
  private String searchKeyword;

}
