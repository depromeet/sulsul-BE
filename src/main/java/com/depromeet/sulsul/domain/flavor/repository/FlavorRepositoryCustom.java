package com.depromeet.sulsul.domain.flavor.repository;

import com.depromeet.sulsul.domain.flavor.dto.FlavorResponse;

import java.util.List;

public interface FlavorRepositoryCustom {
    List<FlavorResponse> selectAll();
}
