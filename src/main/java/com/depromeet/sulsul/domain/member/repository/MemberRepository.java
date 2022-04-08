package com.depromeet.sulsul.domain.member.repository;

import com.depromeet.sulsul.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom{

}
