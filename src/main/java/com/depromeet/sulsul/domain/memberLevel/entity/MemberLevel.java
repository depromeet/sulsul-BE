package com.depromeet.sulsul.domain.memberLevel.entity;

import com.depromeet.sulsul.domain.memberLevel.dto.MemberLevelResponseDto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Builder
public class MemberLevel {
  @Id
  @Column(name = "member_level_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Integer tier;
  private String imageUrl;
  private Integer req;

  public static MemberLevelResponseDto toDto(MemberLevel memberLevel){
    return MemberLevelResponseDto.builder()
        .id(memberLevel.getId())
        .tier(memberLevel.getTier())
        .imageUrl(memberLevel.getImageUrl())
        .req(memberLevel.req)
        .build();
  }
}
