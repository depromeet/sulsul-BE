package com.depromeet.sulsul.domain.flavor.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FlavorResponse {

  private Long flavorId;
  private String content;

  @QueryProjection
  public FlavorResponse(Long flavorId, String content) {
    this.flavorId = flavorId;
    this.content = content;
  }
}
