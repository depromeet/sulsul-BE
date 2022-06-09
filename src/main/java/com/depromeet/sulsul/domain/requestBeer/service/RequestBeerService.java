package com.depromeet.sulsul.domain.requestBeer.service;

import static com.depromeet.sulsul.util.PaginationUtil.PAGINATION_SIZE;
import static com.depromeet.sulsul.util.PaginationUtil.isOverPaginationSize;

import com.depromeet.sulsul.common.error.exception.custom.MemberNotFoundException;
import com.depromeet.sulsul.common.response.dto.DescPageableResponseDto;
import com.depromeet.sulsul.common.response.dto.PageableResponseDto;
import com.depromeet.sulsul.domain.member.entity.Member;
import com.depromeet.sulsul.domain.member.repository.MemberRepository;
import com.depromeet.sulsul.domain.requestBeer.dto.RequestBeerRequestDto;
import com.depromeet.sulsul.domain.requestBeer.dto.RequestBeerResponseDto;
import com.depromeet.sulsul.domain.requestBeer.entity.RequestBeer;
import com.depromeet.sulsul.domain.requestBeer.repository.RequestBeerRepository;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class RequestBeerService {

  private final RequestBeerRepository requestBeerRepository;
  private final MemberRepository memberRepository;

  public RequestBeerResponseDto save(RequestBeerRequestDto requestBeerRequestDto, Long memberId){
    Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);
    return new RequestBeerResponseDto(requestBeerRepository.save(new RequestBeer(requestBeerRequestDto, member)));
  }

  @Transactional(readOnly = true)
  public DescPageableResponseDto<RequestBeerResponseDto> find(Long requestBeerId, Long memberId){
    List<RequestBeerResponseDto> byMemberIdWithPageable = requestBeerRepository.findByMemberIdWithPageable(
        requestBeerId, memberId);

    Long resultCount = requestBeerCount(memberId);
    Long cursor = byMemberIdWithPageable.isEmpty() ? null : byMemberIdWithPageable.get(byMemberIdWithPageable.size()-1).getBeerId();

    return DescPageableResponseDto.of(
        resultCount, byMemberIdWithPageable, cursor, PAGINATION_SIZE
    );
  }

  @Transactional(readOnly = true)
  public Long requestBeerCount(Long memberId){
    return requestBeerRepository.countByMemberId(memberId);
  }

}
