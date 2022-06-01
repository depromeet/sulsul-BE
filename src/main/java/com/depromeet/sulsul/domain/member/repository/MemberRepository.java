package com.depromeet.sulsul.domain.member.repository;

import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.domain.member.entity.Member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

  Optional<Member> findByEmail(String email);

  Long findLevelById(Long id);
}
