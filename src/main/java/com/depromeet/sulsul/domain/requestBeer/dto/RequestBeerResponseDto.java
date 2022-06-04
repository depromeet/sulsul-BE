package com.depromeet.sulsul.domain.requestBeer.dto;

import com.depromeet.sulsul.domain.requestBeer.entity.RequestBeer;
import com.depromeet.sulsul.domain.requestBeer.entity.RequestBeerStatus;
import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestBeerResponseDto {
  private String beerName;
  private String beerImageUrl;
  private LocalDateTime requestCompletedAt;
  private String requestRejectionReason;
  private RequestBeerStatus status;

  private LocalDateTime createAt;

  @QueryProjection
  public RequestBeerResponseDto(RequestBeer requestBeer) {
    this.beerName = requestBeer.getBeerName();
    this.beerImageUrl = requestBeer.getBeerImageUrl();
    this.requestCompletedAt = requestBeer.getRequestCompletedAt();
    this.requestRejectionReason = requestBeer.getRequestRejectionReason();
    this.status = requestBeer.getStatus();
    this.createAt = requestBeer.getCreatedAt();
  }

  @QueryProjection
  public RequestBeerResponseDto(String beerName, String beerImageUrl,
      LocalDateTime requestCompletedAt, String requestRejectionReason,
      RequestBeerStatus status, LocalDateTime createAt) {
    this.beerName = beerName;
    this.beerImageUrl = beerImageUrl;
    this.requestCompletedAt = requestCompletedAt;
    this.requestRejectionReason = requestRejectionReason;
    this.status = status;
    this.createAt = createAt;
  }
}
