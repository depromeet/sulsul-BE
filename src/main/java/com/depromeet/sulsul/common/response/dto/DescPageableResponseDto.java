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
public class DescPageableResponseDto<T> implements Serializable {

  private boolean success;
  private Long resultCount;
  private List<T> contents;
  private Boolean hasNext = false;
  private Long nextCursor;
  private ErrorResponseDto error;

  private DescPageableResponseDto(ErrorResponseDto errorResponseDto) {
    this.error = errorResponseDto;
  }

  private DescPageableResponseDto(List<T> contents, Long nextCursor, int paginationSize) {
    paginate(contents, nextCursor, paginationSize);
    this.contents = contents;
    this.success = true;
  }

  private DescPageableResponseDto(long resultCount, List<T> contents, Long nextCursor, int paginationSize) {
    paginate(contents, nextCursor, paginationSize);
    this.resultCount = resultCount;
    this.contents = contents;
    this.success = true;
  }

  private void paginate(List<T> contents, Long cursor, int paginationSize) {
    if (isOverPaginationSize(contents, paginationSize)) {
      this.hasNext = true;
      contents.remove(paginationSize);
      setCursor(cursor);
    }
  }

  private void setCursor(Long cursor) {
    if (cursor == null) {
      this.nextCursor = null;
      return;
    }
    this.nextCursor = (cursor > 0) ? cursor : 0;
  }

  private boolean isOverPaginationSize(List<T> contents, int size) {
    return contents.size() > size;
  }

  public static <T> DescPageableResponseDto<T> of(List<T> contents, Long nextCursor,
      int paginationSize) {
    return new DescPageableResponseDto<T>(contents, nextCursor, paginationSize);
  }

  public static <T> DescPageableResponseDto<T> of(long resultCount, List<T> contents, Long nextCursor,
      int paginationSize) {
    return new DescPageableResponseDto<>(resultCount, contents, nextCursor, paginationSize);
  }

  public static DescPageableResponseDto<?> ERROR(Throwable throwable, HttpStatus status) {
    return new DescPageableResponseDto(ErrorResponseDto.of(throwable, status));
  }

  public static DescPageableResponseDto<?> ERROR(String errorMessage, HttpStatus status) {
    return new DescPageableResponseDto(ErrorResponseDto.of(errorMessage, status));
  }
}


