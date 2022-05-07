package com.depromeet.sulsul.domain.country.repository;

import com.depromeet.sulsul.domain.country.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    List<Country> findAllByContinentId(Long continentId);
}
