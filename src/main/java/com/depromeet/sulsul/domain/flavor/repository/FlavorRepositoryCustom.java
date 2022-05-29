package com.depromeet.sulsul.domain.flavor.repository;

import com.depromeet.sulsul.domain.flavor.dto.FlavorResponse;
import com.depromeet.sulsul.domain.flavor.dto.FlavorResponseDto;
import com.depromeet.sulsul.domain.flavor.entity.Flavor;
import java.util.List;

public interface FlavorRepositoryCustom {
    List<FlavorResponse> selectAll();
    List<FlavorResponseDto> findTopThreeFlavorsByCount(Long beerId);
}
