package com.depromeet.sulsul.domain.beer.service;

import com.depromeet.sulsul.common.dto.EnumValue;
import com.depromeet.sulsul.common.response.dto.PageableResponse;
import com.depromeet.sulsul.domain.beer.dto.BeerDetail;
import com.depromeet.sulsul.domain.beer.dto.BeerDto;
import com.depromeet.sulsul.domain.beer.dto.BeerFilterSortRequest;
import com.depromeet.sulsul.domain.beer.dto.BeerRequest;
import com.depromeet.sulsul.domain.beer.dto.BeerUpdateRequest;
import com.depromeet.sulsul.domain.beer.entity.Beer;
import com.depromeet.sulsul.domain.beer.entity.BeerType;
import com.depromeet.sulsul.domain.beer.repository.BeerRepository;
import com.depromeet.sulsul.domain.beer.repository.BeerRepositoryCustom;
import com.depromeet.sulsul.domain.continent.dto.ContinentDto;
import com.depromeet.sulsul.domain.continent.entity.Continent;
import com.depromeet.sulsul.domain.country.dto.CountryDto;
import com.depromeet.sulsul.domain.country.entity.Country;
import com.depromeet.sulsul.domain.country.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.depromeet.sulsul.util.PaginationUtil.PAGINATION_SIZE;
import static com.depromeet.sulsul.util.PaginationUtil.isOverPaginationSize;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BeerService {

    private final BeerRepository beerRepository;
    private final BeerRepositoryCustom beerRepositoryCustom;
    private final CountryRepository countryRepository;

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

    @Transactional
    public void save(BeerRequest beerRequest) {
        beerRepository.save(new Beer(
                countryRepository.getById(beerRequest.getCountryId()),
                beerRequest));
    }

    public BeerDetail findById(Long memberId, Long beerId) {
        return beerRepositoryCustom.findById(memberId, beerId);
    }

    public List<EnumValue> findTypes() {
        return PropertyUtil.toEnumValues(BeerType.class);
}
