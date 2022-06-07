package com.depromeet.sulsul.domain.requestBeer.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestBeerRequestDto {

  @NotBlank
  @Size(max = 32)
  private String beerName;

  // TODO: List<String> 으로 전환 필요. (validation 포함)
  // TODO: @Size(max = 2)
  private String beerImageUrl;
}
