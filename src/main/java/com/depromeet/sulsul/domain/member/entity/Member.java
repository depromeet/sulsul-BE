package com.depromeet.sulsul.domain.member.entity;

import javax.persistence.*;

import com.depromeet.sulsul.domain.member.dto.RoleType;
import com.depromeet.sulsul.domain.record.entity.Record;
import com.depromeet.sulsul.domain.review.entity.Review;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

  @Id @Column(name = "member_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany(mappedBy = "member")
  private List<Record> records = new ArrayList<>();

  @OneToMany(mappedBy = "member")
  private List<Review> reviews = new ArrayList<>();

  @Enumerated(EnumType.STRING)
  private RoleType role;

  private String email;
  private String name;
  private String profileUrl;
  private String phoneNumber;

  public String getAuthority(){
    return role.getAuthority();
  }
}
