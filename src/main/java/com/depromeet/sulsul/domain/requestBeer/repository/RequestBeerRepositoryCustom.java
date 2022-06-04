package com.depromeet.sulsul.domain.requestBeer.repository;

import com.depromeet.sulsul.domain.requestBeer.dto.RequestBeerResponseDto;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestBeerRepositoryCustom {
  List<RequestBeerResponseDto> findByMemberIdWithPageable(Long requestBeerResponseId, Long memberId);
}
