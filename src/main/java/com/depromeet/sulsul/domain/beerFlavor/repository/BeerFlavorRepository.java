package com.depromeet.sulsul.domain.beerFlavor.repository;

import com.depromeet.sulsul.domain.beerFlavor.entity.BeerFlavor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerFlavorRepository extends JpaRepository<BeerFlavor, Long> {

}
