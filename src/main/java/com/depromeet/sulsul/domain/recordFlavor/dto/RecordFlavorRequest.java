package com.depromeet.sulsul.domain.recordFlavor.dto;

import com.depromeet.sulsul.domain.flavor.entity.Flavor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecordFlavorRequest {

  private Long beerId;
  private List<Flavor> flavors = new ArrayList<>();
}
