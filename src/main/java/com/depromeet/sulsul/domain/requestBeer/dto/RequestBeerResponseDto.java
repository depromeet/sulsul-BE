package com.depromeet.sulsul.domain.requestBeer.dto;

import com.depromeet.sulsul.domain.requestBeer.entity.RequestBeer;
import com.depromeet.sulsul.domain.requestBeer.entity.RequestBeerStatus;
import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestBeerResponseDto {
  private Long beerId;
  private String beerName;
  private List<String> beerImageUrls;
  private LocalDateTime requestCompletedAt;
  private String requestRejectionReason;
  private RequestBeerStatus status;

  private LocalDateTime createdAt;

  public RequestBeerResponseDto(RequestBeer requestBeer) {
    this.beerId = requestBeer.getRequestBeerId();
    this.beerName = requestBeer.getBeerName();
    this.beerImageUrls = setBeerImageUrls(requestBeer.getBeerImageUrlFirst(), requestBeer.getBeerImageUrlSecond());
    this.requestCompletedAt = requestBeer.getRequestCompletedAt();
    this.requestRejectionReason = requestBeer.getRequestRejectionReason();
    this.status = requestBeer.getStatus();
    this.createdAt = requestBeer.getCreatedAt();
  }

  @QueryProjection
  public RequestBeerResponseDto(Long beerId, String beerName, String beerImageUrlFirst, String beerImageUrlSecond,
      LocalDateTime requestCompletedAt, String requestRejectionReason,
      RequestBeerStatus status, LocalDateTime createAt) {
    this.beerId = beerId;
    this.beerName = beerName;
    this.beerImageUrls = setBeerImageUrls(beerImageUrlFirst, beerImageUrlSecond);
    this.requestCompletedAt = requestCompletedAt;
    this.requestRejectionReason = requestRejectionReason;
    this.status = status;
    this.createdAt = createAt;
  }

  private List<String> setBeerImageUrls(String imageUrlFirst, String imageUrlSecond){
    List<String> beerImageUrls = new ArrayList<>();
    if(!StringUtils.isBlank(imageUrlFirst)){
      beerImageUrls.add(imageUrlFirst);
    }
    if(!StringUtils.isBlank(imageUrlSecond)){
      beerImageUrls.add(imageUrlSecond);
    }
    return beerImageUrls;
  }
}
