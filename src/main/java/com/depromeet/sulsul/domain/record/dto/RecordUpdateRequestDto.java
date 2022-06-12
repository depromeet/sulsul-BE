package com.depromeet.sulsul.domain.record.dto;

import com.depromeet.sulsul.domain.record.entity.Record;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RecordUpdateRequestDto {
  private Long recordId;
  private String imageUrl;

  @Size(max = 256)
  private String content;

  @Size(min = 1, max = 3)
  private List<Long> FlavorIds = new ArrayList<>();
  private Boolean isPublic;

  @NotNull
  @Min(value = 1)
  @Max(value = 5)
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
