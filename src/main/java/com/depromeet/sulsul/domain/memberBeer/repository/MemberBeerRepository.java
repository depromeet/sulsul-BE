package com.depromeet.sulsul.domain.memberBeer.repository;

import com.depromeet.sulsul.domain.memberBeer.entity.MemberBeer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberBeerRepository extends JpaRepository<MemberBeer, Long>,
    MemberBeerRepositoryCustom, QuerydslPredicateExecutor<MemberBeer> {

  void deleteByBeerIdAndMemberId(Long beerId, Long memberId);

  boolean existsByBeerIdAndMemberId(Long beerId, Long memberId);
}
