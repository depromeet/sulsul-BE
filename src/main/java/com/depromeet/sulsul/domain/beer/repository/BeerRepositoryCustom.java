package com.depromeet.sulsul.domain.beer.repository;

import com.depromeet.sulsul.domain.beer.entity.Beer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeerRepositoryCustom {

    List<Beer> findByIdWithPageable(Long beerId);
}
