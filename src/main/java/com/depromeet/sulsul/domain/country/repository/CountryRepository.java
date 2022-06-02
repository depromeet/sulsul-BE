package com.depromeet.sulsul.domain.country.repository;

import com.depromeet.sulsul.domain.country.entity.Country;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long>, CountryRepositoryCustom {

  List<Country> findAllByContinentId(Long continentId);
}
