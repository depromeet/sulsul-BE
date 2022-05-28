package com.depromeet.sulsul.domain.record.dto;

import com.depromeet.sulsul.domain.flavor.entity.Flavor;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecordRequestDto {

  private String content;
  private Long beerId;
  private List<Flavor> Flavors = new ArrayList<>();
  private Boolean isPublic;
  private Integer feel;
}