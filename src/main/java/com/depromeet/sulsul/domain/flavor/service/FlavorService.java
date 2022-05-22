package com.depromeet.sulsul.domain.flavor.service;

import com.depromeet.sulsul.domain.flavor.dto.FlavorResponse;
import com.depromeet.sulsul.domain.flavor.repository.FlavorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FlavorService {

    private final FlavorRepository flavorRepository;

    public List<FlavorResponse> findAll() {
        return flavorRepository.selectAll();
    }
}
