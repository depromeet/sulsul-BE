package com.depromeet.sulsul.domain.requestBeer.dto;

import com.depromeet.sulsul.util.PaginationUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RequestBeerFindDto {
  private Long cursor;
  private final Integer limit = PaginationUtil.PAGINATION_SIZE+1;
}
