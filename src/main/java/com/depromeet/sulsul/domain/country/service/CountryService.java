package com.depromeet.sulsul.domain.country.service;

import com.depromeet.sulsul.domain.country.dto.CountryDto;
import com.depromeet.sulsul.domain.country.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;

    public List<CountryDto> findAllByContinentId(Long continentId) {
        if (continentId == null) {
            return countryRepository.findAll()
                    .stream()
                    .map(country -> new CountryDto(country))
                    .collect(Collectors.toList());
        }

        return countryRepository.findAllByContinentId(continentId)
                .stream()
                .map(country -> new CountryDto(country))
                .collect(Collectors.toList());
    }
}
