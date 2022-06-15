package com.depromeet.sulsul.domain.record.dto;

import com.depromeet.sulsul.domain.record.entity.Record;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
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
  private String content;

  @Size(min = 1, max = 3)
  @ApiModelProperty(name = "flavorIds", dataType = "List", example = "[1, 2, 3]")
  private List<Long> flavorIds = new ArrayList<>();
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