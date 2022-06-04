package com.depromeet.sulsul.domain.requestBeer.repository;

import com.depromeet.sulsul.domain.requestBeer.entity.RequestBeer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestBeerRepository extends JpaRepository<RequestBeer, Long>, RequestBeerRepositoryCustom,
    QuerydslPredicateExecutor<RequestBeer> {
  Long countByMemberId(Long memberId);
}
