package com.depromeet.sulsul.domain.member.entity;

import javax.persistence.*;

import com.depromeet.sulsul.domain.record.entity.Record;
import com.depromeet.sulsul.domain.review.entity.Review;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

  @Id @Column(name = "member_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany(mappedBy = "member")
  private List<Record> records = new ArrayList<>();

  @OneToMany(mappedBy = "member")
  private List<Review> reviews = new ArrayList<>();

  private String email;
  private String name;
  private String profileUrl;
  private String phoneNumber;

  public Member(Long id, String email, String name, String profileUrl, String phoneNumber) {
    this.id = id;
    this.email = email;
    this.name = name;
    this.profileUrl = profileUrl;
    this.phoneNumber = phoneNumber;
  }
}
