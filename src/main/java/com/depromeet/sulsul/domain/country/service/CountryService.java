package com.depromeet.sulsul.domain.country.service;

import com.depromeet.sulsul.domain.country.dto.CountryDto;
import com.depromeet.sulsul.domain.country.repository.CountryRepository;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CountryService {

  private final CountryRepository countryRepository;

  @Transactional(readOnly = true)
  public List<CountryDto> findAllByContinentId(Long continentId) {
    if (continentId == null) {
      return countryRepository.findAll()
          .stream()
          .map(CountryDto::new)
          .collect(Collectors.toList());
    }

    return countryRepository.findAllByContinentId(continentId)
        .stream()
        .map(CountryDto::new)
        .collect(Collectors.toList());
  }

  public Long findCountryCountByMemberId(Long id) {
    return countryRepository.findCountryCountByMemberId(id);
  }
}
