package com.depromeet.sulsul.common.request;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class ReadRequest {

  @Nullable
  private String query;   // 검색어

  @Nullable
  private Long cursor;    // 커서 or 오프셋
  private int limit;     // Page 단위 개수

  @JsonProperty(value = "filter")
  private Filter filter;  // 필터 조건

  @JsonProperty(value = "sortBy")
  private List<SortCondition> sortBy; // 정렬 조건

}
