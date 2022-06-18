package com.depromeet.sulsul.domain.record.dto;

import com.depromeet.sulsul.domain.record.entity.Record;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "기록 생성 요청 정보 DTO", description = "기록 생성 시 대상 맥주 ID, 이미지 URL, 기록 내용 등을 포함한 DTO")
public class RecordRequestDto {

  @ApiModelProperty(value = "맥주 ID", example = "1")
  private Long beerId;

  @ApiModelProperty(value = "맥주 사진 URL", example = "https://sulsul-media-bucket.s3.ap-northeast-2.amazonaws.com/COUNTRY/germany.png")
  private String imageUrl;

  @Size(max = 256)
  @ApiModelProperty(value = "맥주 기록 내용", example = "동기들이랑 편하게 즐기기 좋았음. 깔끔했다!")
  private String content;

  @Size(min = 1, max = 3)
  @ApiModelProperty(value = "(리스트) 맥주 맛 ID", example = "[1, 6, 8]")
  private List<Long> flavorIds = new ArrayList<>();

  @ApiModelProperty(value = "기록에 대한 공개 여부", example = "true")
  private Boolean isPublic;

  @NotNull
  @Min(value = 1)
  @Max(value = 5)
  @ApiModelProperty(value = "사용자가 매기는 맥주 평점", example = "4")
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