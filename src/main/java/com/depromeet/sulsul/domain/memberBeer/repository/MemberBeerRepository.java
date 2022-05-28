package com.depromeet.sulsul.domain.memberBeer.repository;

import com.depromeet.sulsul.domain.memberBeer.entity.MemberBeer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberBeerRepository extends JpaRepository<MemberBeer, Long> {
  Optional<MemberBeer> findMemberBeerByBeerIdAndMemberId(Long beerId, Long memberId);
  MemberBeer getMemberBeerByBeerIdAndMemberId(Long beerId, Long memberId);
}
