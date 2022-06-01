package com.depromeet.sulsul.domain.flavor.service;

import com.depromeet.sulsul.domain.flavor.dto.FlavorResponse;
import com.depromeet.sulsul.domain.flavor.dto.FlavorResponseDto;
import com.depromeet.sulsul.domain.flavor.entity.Flavor;
import com.depromeet.sulsul.domain.flavor.repository.FlavorRepository;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FlavorService {

  private final FlavorRepository flavorRepository;

  public List<FlavorResponse> findAll() {
    return flavorRepository.selectAll();
  }


  public List<FlavorResponseDto> findTopFlavors(Long beerId) {
    return flavorRepository.findTopThreeFlavorsByCount(beerId);
  }
}
