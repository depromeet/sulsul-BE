package com.depromeet.sulsul.domain.beer.dto;

import com.depromeet.sulsul.domain.beer.entity.BeerType;
import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BeerRequestDto implements Serializable {

  private static final long serialVersionUID = -2120618888041262425L;

  private Long countryId;
  private BeerType type;
  private String nameKor;
  private String nameEng;
  private String imageUrl;
  private String content;
  private Float alcohol;
  private Integer price;
  private Integer volume;
}
