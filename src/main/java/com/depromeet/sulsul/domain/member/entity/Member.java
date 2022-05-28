package com.depromeet.sulsul.domain.member.entity;

import com.depromeet.sulsul.domain.record.entity.Record;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(exclude = "records")
@EqualsAndHashCode(exclude = "record")
public class Member {

  @Id
  @Column(name = "member_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany(mappedBy = "member")
  private List<Record> records = new ArrayList<>();

  private String email;
  private String name;
  private String profileUrl;
  private String phoneNumber;
}
