package com.depromeet.sulsul.domain.beer.repository;

import com.depromeet.sulsul.domain.beer.dto.BeerDto;
import com.depromeet.sulsul.domain.beer.dto.BeerFilterSortRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeerRepositoryCustom {

    List<BeerDto> findAllWithPageableFilterSort(Long memberId, Long beerId, BeerFilterSortRequest beerFilterSortRequest);
}
