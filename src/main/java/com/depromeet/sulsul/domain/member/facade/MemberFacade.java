package com.depromeet.sulsul.domain.member.facade;

import com.depromeet.sulsul.domain.beer.service.BeerService;
import com.depromeet.sulsul.domain.country.service.CountryService;
import com.depromeet.sulsul.domain.member.dto.MyPageRequestDto;
import com.depromeet.sulsul.domain.record.service.RecordService;
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

  /**
   * - 마신 맥주 count - 기록 갯수 count - 여행한 나라 count - 미등록 맥주 요청 갯수 count - 찜한 맥주 갯수 count
   */
  @Transactional(readOnly = true)
  public MyPageRequestDto findMyPageByMemberId(Long id) {
    Long beerCount = beerService.findBeerCountByMemberId(id);
    Long recordCount = recordService.findRecordCountByMemberId(id);
    Long countryCount = countryService.findCountryCountByMemberId(id);

    return MyPageRequestDto.of(beerCount, recordCount, countryCount, null, null);
  }
}
