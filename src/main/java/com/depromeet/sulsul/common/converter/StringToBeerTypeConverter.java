package com.depromeet.sulsul.common.converter;

import com.depromeet.sulsul.domain.beer.entity.BeerType;
import org.springframework.core.convert.converter.Converter;


public class StringToBeerTypeConverter implements Converter<String, BeerType> {

    public BeerType convert(String source) {
        try {
            source.replaceAll("-", "_");
            return BeerType.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
