package com.depromeet.sulsul.common.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "무한 스크롤 조회 요청 정보", description = "검색어, 커서, 조회 단위 갯수, 필터, 정렬 기준을 포함한 RequestDto")
public class ReadRequest {

  @Nullable
  @ApiModelProperty(value = "검색어", example = "독일")
  private String query;   // 검색어

  @Nullable
  @ApiModelProperty(value = "다음 페이지 시작 offset 값", example = "15")
  private Long cursor;    // 커서 or 오프셋

  @ApiModelProperty(value = "페이지 조회 단위 개수", example = "15")
  private int limit;     // Page 단위 개수

  @JsonProperty(value = "filter")
  @ApiModelProperty(value = "필터 값")
  private Filter filter;  // 필터 조건

  @JsonProperty(value = "sortBy")
  @ApiModelProperty(value = "정렬 조건", example = "[createdAt.asc, updatedAt.desc]")
  private List<SortCondition> sortBy; // 정렬 조건

}
