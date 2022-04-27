package com.depromeet.sulsul.domain.beer.repository;

import com.depromeet.sulsul.domain.beer.dto.BeerDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeerRepositoryCustom {

    List<BeerDto> findByIdWithPageableV1(Long memberId, Long beerId);
}
