package com.depromeet.sulsul.domain.member.entity;

import com.depromeet.sulsul.domain.member.dto.RoleType;
import com.depromeet.sulsul.domain.record.entity.Record;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = "records")
@EqualsAndHashCode(exclude = "records")
public class Member {

  @Id
  @Column(name = "member_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany(mappedBy = "member")
  private List<Record> records = new ArrayList<>();

  @Enumerated(EnumType.STRING)
  private RoleType role;

  private String email;
  private String name;
  private String profileUrl;
  private String phoneNumber;

  public String getAuthority() {
    return role.getAuthority();
  }

  @Builder
  public Member(Long id, List<Record> records, RoleType role, String email, String name,
      String profileUrl, String phoneNumber) {
    this.id = id;
    this.records = records;
    this.role = role;
    this.email = email;
    this.name = name;
    this.profileUrl = profileUrl;
    this.phoneNumber = phoneNumber;
  }

  public Member update(String name, String email) {
    this.name = name;
    this.email = email;
    return this;
  }

  public void updateName(String name) {
    this.name = name;
  }
}
