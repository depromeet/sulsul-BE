package com.depromeet.sulsul.domain.record.dto;

import com.depromeet.sulsul.domain.record.entity.Record;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RecordRequest {

  private Long beerId;
  private Long memberId;
  private String content;
  private Integer feel;
  private Boolean isPublic;
  private Integer score;

  private List<Long> flavorIds = new ArrayList<>();
  public Record toEntity() {
    return Record.builder()
        .content(this.content)
        .feel(this.feel)
        .isPublic(this.isPublic)
        .score(this.score)
        .build();
  }
}
