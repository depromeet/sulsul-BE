package com.depromeet.sulsul.domain.beer.service;

import com.depromeet.sulsul.common.response.dto.PageableResponse;
import com.depromeet.sulsul.domain.beer.dto.BeerDto;
import com.depromeet.sulsul.domain.beer.dto.BeerRequest;
import com.depromeet.sulsul.domain.beer.entity.Beer;
import com.depromeet.sulsul.domain.beer.repository.BeerRepository;
import com.depromeet.sulsul.domain.beer.repository.BeerRepositoryCustom;
import com.depromeet.sulsul.domain.beer.repository.BeerRepositoryCustomImpl;
import com.depromeet.sulsul.domain.continent.dto.ContinentDto;
import com.depromeet.sulsul.domain.continent.entity.Continent;
import com.depromeet.sulsul.domain.country.dto.CountryDto;
import com.depromeet.sulsul.domain.country.entity.Country;
import com.depromeet.sulsul.domain.country.repository.CountryRepository;
import com.depromeet.sulsul.util.PropertyUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BeerService {

    private final BeerRepository beerRepository;
    private final BeerRepositoryCustom beerRepositoryCustom;
    private final CountryRepository countryRepository;

    public BeerService(BeerRepository beerRepository, BeerRepositoryCustomImpl beerRepositoryCustom, CountryRepository countryRepository) {
        this.beerRepository = beerRepository;
        this.beerRepositoryCustom = beerRepositoryCustom;
        this.countryRepository = countryRepository;
    }

    @Transactional(readOnly = true)
    public PageableResponse<BeerDto> findAll(Long beerId) {

        List<BeerDto> beerDtosWithPageable = beerRepositoryCustom.findByIdWithPageable(beerId)
                .stream()
                .map(beer -> {
                    Country country = beer.getCountry();
                    Continent continent = country.getContinent();
                    CountryDto countryDto = new CountryDto(country.getId(), country.getName(),
                            new ContinentDto(continent.getId(), continent.getName()));

                    return new BeerDto(
                            countryDto,
                            beer
                    );
                })
                .collect(Collectors.toList());

        PageableResponse<BeerDto> beerPageableResponse = new PageableResponse<>(false, null);
        if (isOverPaginationSize(beerDtosWithPageable)) {
            beerDtosWithPageable.remove(PropertyUtil.PAGINATION_SIZE);
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

    private boolean isOverPaginationSize(List<BeerDto> beersWithPageable) {
        return beersWithPageable.size() == PropertyUtil.PAGINATION_SIZE + 1;
    }
}
