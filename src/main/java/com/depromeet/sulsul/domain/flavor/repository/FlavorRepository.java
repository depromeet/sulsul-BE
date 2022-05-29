package com.depromeet.sulsul.domain.flavor.repository;

import com.depromeet.sulsul.domain.flavor.entity.Flavor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FlavorRepository extends JpaRepository<Flavor, Long>, FlavorRepositoryCustom,
    QuerydslPredicateExecutor<Flavor> {

}
