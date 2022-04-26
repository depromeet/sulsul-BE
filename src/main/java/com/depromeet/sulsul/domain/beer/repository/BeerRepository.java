package com.depromeet.sulsul.domain.beer.repository;

import com.depromeet.sulsul.domain.beer.entity.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Long> {
}
