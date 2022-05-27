package com.depromeet.sulsul.common.response.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageableResponse<T> implements Serializable {

  private boolean success;
  private List<T> contents;
  private Boolean hasNext = false;
  private Long nextCursor;
  private ErrorResponseDto error;


  private PageableResponse(ErrorResponseDto errorResponseDto) {
    this.error = errorResponseDto;
  }

  private PageableResponse(List<T> contents, Long nextCursor, int paginationSize) {
    paginate(contents, nextCursor, paginationSize);
    this.contents = contents;
    this.success = true;
  }

  private void paginate(List<T> contents, Long cursor, int paginationSize) {
    if (isOverPaginationSize(contents, paginationSize)) {
      this.hasNext = true;
      contents.remove(paginationSize);
      setCursor(cursor, paginationSize);
    }
  }

  private void setCursor(Long cursor, int paginationSize) {
    if (cursor == null) {
      this.nextCursor = Integer.toUnsignedLong(paginationSize);
      return;
    }
    this.nextCursor = cursor + paginationSize;
  }

  private boolean isOverPaginationSize(List<T> contents, int size) {
    return contents.size() > size;
  }

  public static <T> PageableResponse<T> of(List<T> contents, Long nextCursor, int paginationSize) {
    return new PageableResponse<>(contents, nextCursor, paginationSize);
  }

  public static PageableResponse<?> ERROR(Throwable throwable, HttpStatus status) {
    return new PageableResponse(ErrorResponseDto.of(throwable, status));
  }

  public static PageableResponse<?> ERROR(String errorMessage, HttpStatus status) {
    return new PageableResponse(ErrorResponseDto.of(errorMessage, status));
  }
}
