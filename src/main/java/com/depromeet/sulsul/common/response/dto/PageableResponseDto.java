package com.depromeet.sulsul.common.response.dto;

import com.depromeet.sulsul.common.error.dto.ErrorResponseDto;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageableResponseDto<T> implements Serializable {

  private boolean success;
  private Long resultCount;
  private List<T> contents;
  private Boolean hasNext = false;
  private Long nextCursor;
  private ErrorResponseDto error;


  private PageableResponseDto(ErrorResponseDto errorResponseDto) {
    this.error = errorResponseDto;
  }

  private PageableResponseDto(List<T> contents, Long nextCursor, int paginationSize) {
    paginate(contents, nextCursor, paginationSize);
    this.contents = contents;
    this.success = true;
  }

  private PageableResponseDto(long resultCount, List<T> contents, Long nextCursor, int paginationSize) {
    paginate(contents, nextCursor, paginationSize);
    this.resultCount = resultCount;
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

  public static <T> PageableResponseDto<T> of(List<T> contents, Long nextCursor,
      int paginationSize) {
    return new PageableResponseDto<>(contents, nextCursor, paginationSize);
  }

  public static <T> PageableResponseDto<T> of(long resultCount, List<T> contents, Long nextCursor,
      int paginationSize) {
    return new PageableResponseDto<>(resultCount, contents, nextCursor, paginationSize);
  }

  public static PageableResponseDto<?> ERROR(Throwable throwable, HttpStatus status) {
    return new PageableResponseDto(ErrorResponseDto.of(throwable, status));
  }

  public static PageableResponseDto<?> ERROR(String errorMessage, HttpStatus status) {
    return new PageableResponseDto(ErrorResponseDto.of(errorMessage, status));
  }
}
