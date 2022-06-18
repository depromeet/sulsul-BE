package com.depromeet.sulsul.domain.member.facade;

import com.depromeet.sulsul.domain.beer.service.BeerService;
import com.depromeet.sulsul.domain.country.service.CountryService;
import com.depromeet.sulsul.domain.member.dto.MyPageRequestDto;
import com.depromeet.sulsul.domain.member.dto.MyPageResponseDto;
import com.depromeet.sulsul.domain.member.service.MemberService;
import com.depromeet.sulsul.domain.memberBeer.service.MemberBeerService;
import com.depromeet.sulsul.domain.memberBeerFlavor.service.MemberBeerFlavorService;
import com.depromeet.sulsul.domain.record.service.RecordService;
import com.depromeet.sulsul.domain.requestBeer.service.RequestBeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberFacade {

  private final BeerService beerService;
  private final CountryService countryService;
  private final RecordService recordService;
  private final MemberBeerService memberBeerService;
  private final RequestBeerService requestBeerService;
  private final MemberService memberService;

  private final MemberBeerFlavorService memberBeerFlavorService;

  /**
   * - 마신 맥주 count - 기록 갯수 count - 여행한 나라 count - 미등록 맥주 요청 갯수 count - 찜한 맥주 갯수 count
   */
  @Transactional(readOnly = true)
  public MyPageResponseDto findMyPageByMemberId(Long memberId) {
    Long beerCount = beerService.findBeerCountByMemberId(memberId);
    Long recordCount = recordService.findRecordCountByMemberId(memberId);
    Long countryCount = countryService.findCountryCountByMemberId(memberId);
    Long memberBeerCount = memberBeerService.findMemberBeerCountByMemberId(memberId);
    Long requestBeerCount = requestBeerService.requestBeerCount(memberId);
    String nickName = memberService.findById(memberId).getName();

    return MyPageResponseDto.of(beerCount, recordCount, countryCount, memberBeerCount, requestBeerCount, nickName);
  }

  public void deleteMember(Long id) {
    memberService.updateDeletedAtById(id);
    memberBeerService.updateDeletedAtByMemberId(id);
    recordService.updateDeletedAtByMemberId(id);
    requestBeerService.updateDeletedAtByMemberId(id);
    memberBeerFlavorService.updateDeletedAtByMemberId(id);
  }
}
