package com.depromeet.sulsul.domain.record.dto;

import com.depromeet.sulsul.domain.record.entity.Record;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RecordRequestDto {

  private Long beerId;
  private String imageUrl;
  private String content;
  private List<Long> FlavorIds = new ArrayList<>();
  private Boolean isPublic;
  private Integer feel;

  public Record toEntity() {
    return Record.builder()
        .content(this.content)
        .feel(this.feel)
        .imageUrl(this.imageUrl)
        .isPublic(this.isPublic)
        .build();
  }
}