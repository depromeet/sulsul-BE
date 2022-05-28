package com.depromeet.sulsul.domain.memberBeer.service;

import com.depromeet.sulsul.common.entity.BaseEntity;
import com.depromeet.sulsul.domain.beer.entity.Beer;
import com.depromeet.sulsul.domain.beer.repository.BeerRepository;
import com.depromeet.sulsul.domain.member.entity.Member;
import com.depromeet.sulsul.domain.member.repository.MemberRepository;
import com.depromeet.sulsul.domain.memberBeer.entity.MemberBeer;
import com.depromeet.sulsul.domain.memberBeer.repository.MemberBeerRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberBeerService {
  private final MemberBeerRepository memberBeerRepository;
  private final BeerRepository beerRepository;
  private final MemberRepository memberRepository;


  // TODO : 찜한 맥주의 경우 모든 경우 해당 member가 본인인지 검증하는 로직이 필요하다.

  @Transactional
  public void save(Long beerId, Long memberId){
    Optional<MemberBeer> memberBeer = memberBeerRepository.findMemberBeerByBeerIdAndMemberId(beerId, memberId);

    Beer beer = beerRepository.getById(beerId);
    Member member = memberRepository.getById(memberId);

    memberBeer.ifPresentOrElse(BaseEntity::restore
      , () -> memberBeerRepository.save(new MemberBeer(beer, member)));
  }

  @Transactional
  public void delete(Long beerId, Long memberId){
    MemberBeer memberBeer = memberBeerRepository.getMemberBeerByBeerIdAndMemberId(beerId, memberId);
    memberBeer.delete();
  }
}
