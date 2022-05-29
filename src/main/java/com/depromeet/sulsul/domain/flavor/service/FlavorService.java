package com.depromeet.sulsul.domain.flavor.service;

import com.depromeet.sulsul.domain.flavor.dto.FlavorResponse;
import com.depromeet.sulsul.domain.flavor.repository.FlavorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class FlavorService {

    private final FlavorRepository flavorRepository;

    @Transactional(readOnly = true)
    public List<FlavorResponse> findAll() {
        return flavorRepository.selectAll();
    }
}
