package com.depromeet.sulsul.domain.continent.service;

import com.depromeet.sulsul.domain.continent.dto.ContinentDto;
import com.depromeet.sulsul.domain.continent.repository.ContinentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ContinentService {

  private final ContinentRepository continentRepository;

  @Transactional(readOnly = true)
  public List<ContinentDto> findAll() {
    return continentRepository.findAll()
        .stream()
        .map(ContinentDto::new)
        .collect(Collectors.toList());
  }
}
