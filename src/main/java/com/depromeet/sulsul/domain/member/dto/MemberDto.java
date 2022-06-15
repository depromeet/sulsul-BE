package com.depromeet.sulsul.domain.member.dto;

import com.depromeet.sulsul.domain.memberLevel.dto.MemberLevelResponseDto;
import com.depromeet.sulsul.domain.memberLevel.entity.MemberLevel;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class MemberDto implements Serializable {

  private static final long serialVersionUID = -3240908099376045282L;
  private Long id;
  private String role;
  private String email;
  private String name;
  private String profileUrl;
  private String phoneNumber;

  private String social;

  private MemberLevelResponseDto memberLevelResponseDto;

  @Builder
  @QueryProjection
  public MemberDto(Long id, String role, String email, String name, String profileUrl, String phoneNumber, MemberLevel memberLevel) {
    this.id = id;
    this.role = role;
    this.email = email;
    this.name = name;
    this.profileUrl = profileUrl;
    this.phoneNumber = phoneNumber;
    this.memberLevelResponseDto = MemberLevel.toDto(memberLevel);
  }
}
