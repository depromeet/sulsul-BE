package com.depromeet.sulsul.common.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SortCondition {
  NAME_KOR_ASC("nameKor.asc"), NAME_KOR_DESC("nameKor.desc"), NAME_ENG_ASC(
      "nameEng.asc"), NAME_ENG_DESC("nameEng.desc"), ALCOHOL_ASC("alcohol.asc"), ALCOHOL_DESC(
      "alcohol.desc"), RECORD_ASC("record.asc"), RECORD_DESC("record.desc"), ID_ASC(
      "createdAt.asc"), ID_DESC("createdAt.desc"), UPDATED_AT_ASC("updatedAt.asc"), UPDATED_AT_DESC(
      "updatedAt.desc");

  private final String request;

  SortCondition() {
    this.request = "createdAt.asc";
  }

  @JsonCreator
  public static SortCondition getSortConditionFromRequest(String value) {

    return Arrays.stream(SortCondition.values())
        .filter(sortCondition -> sortCondition.getRequest().equals(value)).findFirst()
        .orElseThrow(() -> new IllegalArgumentException());
  }

  public String getRequest() {
    return request;
  }
}
