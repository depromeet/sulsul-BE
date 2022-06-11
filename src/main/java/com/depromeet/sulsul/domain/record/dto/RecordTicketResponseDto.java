package com.depromeet.sulsul.domain.record.dto;

import com.depromeet.sulsul.domain.beer.dto.BeerResponseDto;
import com.depromeet.sulsul.domain.beer.entity.Beer;
import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecordTicketResponseDto {
  private Long id;
  private Integer feel;
  private String startCountryEng;
  private String endCountryEng;
  private String startCountryKor;
  private String endCountryKor;
  private String imageUrl;
  private LocalDateTime createdAt;

  private BeerResponseDto beerResponseDto;

  @QueryProjection
  public RecordTicketResponseDto(Long recordId, Beer beer,
      LocalDateTime createdAt, Integer feel, String startCountryEng, String endCountryEng,
      String startCountryKor, String endCountryKor, String imageUrl) {
    this.id = recordId;
    this.beerResponseDto = Beer.toDto(beer);
    this.createdAt = createdAt;
    this.feel = feel;
    this.startCountryEng = startCountryEng;
    this.endCountryEng = endCountryEng;
    this.startCountryKor = startCountryKor;
    this.endCountryKor = endCountryKor;
    this.imageUrl = imageUrl;
  }

}
