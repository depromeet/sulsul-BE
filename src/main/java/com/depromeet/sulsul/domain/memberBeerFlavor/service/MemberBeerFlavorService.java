package com.depromeet.sulsul.domain.memberBeerFlavor.service;

import com.depromeet.sulsul.domain.memberBeerFlavor.repository.MemberBeerFlavorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberBeerFlavorService {

  private final MemberBeerFlavorRepository memberBeerFlavorRepository;

  public void updateDeletedAtByMemberId(Long id) {
    memberBeerFlavorRepository.updateDeletedAtMemberId(id);
  }
}
