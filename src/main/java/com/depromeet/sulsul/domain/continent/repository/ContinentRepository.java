package com.depromeet.sulsul.domain.continent.repository;

import com.depromeet.sulsul.domain.continent.entity.Continent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContinentRepository extends JpaRepository<Continent, Long> {

}
