package com.depromeet.sulsul.common.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReadRequest {

  private String query;   // 검색어
  private Long cursor;    // 커서 or 오프셋
  private int limit;     // Page 단위 개수

  @JsonProperty("filter")
  private Filter filter;  // 필터 조건
  private List<SortCondition> sortBy; // 정렬 조건

}
