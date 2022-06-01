package com.depromeet.sulsul.domain.member.dto;

import java.io.Serializable;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

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

  @Builder
  @QueryProjection
  public MemberDto(Long id, String role, String email, String name, String profileUrl, String phoneNumber) {
    this.id = id;
    this.role = role;
    this.email = email;
    this.name = name;
    this.profileUrl = profileUrl;
    this.phoneNumber = phoneNumber;
  }
}
