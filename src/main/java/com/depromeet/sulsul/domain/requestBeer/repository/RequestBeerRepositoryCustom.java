package com.depromeet.sulsul.domain.requestBeer.repository;

import com.depromeet.sulsul.domain.requestBeer.dto.RequestBeerResponseDto;
import java.util.List;

public interface RequestBeerRepositoryCustom {
  List<RequestBeerResponseDto> findByMemberIdWithPageable(Long requestBeerResponseId, Long memberId);
}
