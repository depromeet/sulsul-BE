package com.depromeet.sulsul.domain.requestBeer.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
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

  @Size(max = 2)
  private List<String> beerImageUrls = new ArrayList<>();
}
