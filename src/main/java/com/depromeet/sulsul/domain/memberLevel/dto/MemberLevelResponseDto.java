package com.depromeet.sulsul.domain.memberLevel.dto;

import com.depromeet.sulsul.domain.memberLevel.entity.MemberLevel;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class MemberLevelResponseDto {
  private Long id;
  private String tier;
  private String imageUrl;
  private Integer req;

  @QueryProjection
  public MemberLevelResponseDto(Long id, String tier, String imageUrl, Integer req) {
    this.id = id;
    this.tier = tier;
    this.imageUrl = imageUrl;
    this.req = req;
  }

  public MemberLevel toEntity(){
    return MemberLevel.builder()
        .id(id)
        .tier(tier)
        .imageUrl(imageUrl)
        .req(req)
        .build();
  }
}
