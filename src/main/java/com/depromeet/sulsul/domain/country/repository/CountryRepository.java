package com.depromeet.sulsul.domain.country.repository;

import com.depromeet.sulsul.domain.country.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
