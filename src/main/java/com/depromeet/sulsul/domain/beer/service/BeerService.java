package com.depromeet.sulsul.domain.beer.service;

import com.depromeet.sulsul.common.dto.EnumValue;
import com.depromeet.sulsul.common.response.dto.PageableResponse;
import com.depromeet.sulsul.domain.beer.dto.*;
import com.depromeet.sulsul.domain.beer.entity.Beer;
import com.depromeet.sulsul.domain.beer.entity.BeerType;
import com.depromeet.sulsul.domain.beer.repository.BeerRepository;
import com.depromeet.sulsul.domain.beer.repository.BeerRepositoryCustom;
import com.depromeet.sulsul.domain.country.repository.CountryRepository;
import com.depromeet.sulsul.util.PropertyUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.depromeet.sulsul.util.PaginationUtil.PAGINATION_SIZE;
import static com.depromeet.sulsul.util.PaginationUtil.isOverPaginationSize;


@Service
@Transactional
@RequiredArgsConstructor
public class BeerService {

    private final BeerRepository beerRepository;
    private final BeerRepositoryCustom beerRepositoryCustom;
    private final CountryRepository countryRepository;

    @Transactional(readOnly = true)
    public PageableResponse<BeerDto> findPageWithFilterRequest(Long memberId, Long beerId, BeerFilterSortRequest beerFilterSortRequest) {
        List<BeerDto> beerDtosWithPageable = beerRepositoryCustom.findAllWithPageableFilterSort(memberId, beerId, beerFilterSortRequest);

        PageableResponse<BeerDto> beerPageableResponse = new PageableResponse<>();
        if (isOverPaginationSize(beerDtosWithPageable)) {
            beerDtosWithPageable.remove(PAGINATION_SIZE);
            beerPageableResponse.setHasNext(true);
        }

        beerPageableResponse.setContents(beerDtosWithPageable);
        return beerPageableResponse;
    }

    public void save(BeerRequest beerRequest) {

        beerRepository.save(new Beer(
                countryRepository.getById(beerRequest.getCountryId()),
                beerRequest));
    }

    @Transactional(readOnly = true)
    public BeerDetail findById(Long memberId, Long beerId) {
        return beerRepositoryCustom.findById(memberId, beerId);
    }

    public List<BeerTypeValue> findTypes() {
        return Arrays
                .stream(BeerType.class.getEnumConstants())
                .map(BeerTypeValue::new)
                .collect(Collectors.toList());
    }
}
