package com.depromeet.sulsul.common.request;

import com.depromeet.sulsul.domain.beer.entity.BeerType;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@ApiModel(value = "맥주 조회 시 필터 정보", description = "맥주 타입, 국가에 대한 필터 객체")
public class Filter {

  @JsonProperty("beerTypes")
  @ApiModelProperty(value = "(리스트) 맥주 타입 핕터 값", example = "[ALE, PILSNER, RADLER]")
  private List<BeerType> beerTypes;

  @JsonProperty("countryIds")
  @ApiModelProperty(value = "(리스트) 국가 필터 값", example = "[1, 3, 6]")
  private List<Long> countryIds;

}
