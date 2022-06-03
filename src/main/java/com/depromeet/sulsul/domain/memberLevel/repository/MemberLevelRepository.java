package com.depromeet.sulsul.domain.memberLevel.repository;

import com.depromeet.sulsul.domain.memberLevel.entity.MemberLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MemberLevelRepository extends JpaRepository<MemberLevel, Long>, MemberLevelRepositoryCustom,
    QuerydslPredicateExecutor<MemberLevel> {
}
