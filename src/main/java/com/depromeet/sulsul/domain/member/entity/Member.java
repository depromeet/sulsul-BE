package com.depromeet.sulsul.domain.member.entity;

import javax.persistence.*;

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


  private String email;
  private String name;
  private String profileUrl;
  private String phoneNumber;

}
