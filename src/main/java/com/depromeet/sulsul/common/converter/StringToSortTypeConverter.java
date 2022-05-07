package com.depromeet.sulsul.common.converter;

import com.depromeet.sulsul.domain.beer.entity.SortType;
import org.springframework.core.convert.converter.Converter;


public class StringToSortTypeConverter implements Converter<String, SortType> {

    public SortType convert(String source) {
        try {
            return SortType.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
