package com.depromeet.sulsul.domain.beer.service;

import com.depromeet.sulsul.common.response.dto.PageableResponse;
import com.depromeet.sulsul.domain.beer.dto.BeerDto;
import com.depromeet.sulsul.domain.beer.dto.BeerRequest;
import org.springframework.stereotype.Service;

@Service
public interface BeerService {
    PageableResponse<BeerDto> findAll(Long beerId);
    PageableResponse<BeerDto> findAll(Long memberId, Long beerId);
    void save(BeerRequest beerRequest);
}
