package com.depromeet.sulsul.domain.flavor.entity;

import com.depromeet.sulsul.common.entity.BaseEntity;
import com.depromeet.sulsul.domain.flavor.dto.FlavorDto;
import com.depromeet.sulsul.domain.recordFlavor.entity.RecordFlavor;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Flavor extends BaseEntity {

  @Id
  @Column(name = "flavor_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String content;

  @OneToMany(mappedBy = "flavor")
  private List<RecordFlavor> recordFlavors = new ArrayList<>();

  public FlavorDto toDto() {
    return FlavorDto.builder()
        .id(id)
        .content(content)
        .build();

  }
}
