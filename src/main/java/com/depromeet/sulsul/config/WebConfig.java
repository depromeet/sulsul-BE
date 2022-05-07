package com.depromeet.sulsul.config;

import com.depromeet.sulsul.common.converter.StringToBeerTypeConverter;
import com.depromeet.sulsul.common.converter.StringToSortTypeConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToSortTypeConverter());
        registry.addConverter(new StringToBeerTypeConverter());
    }
}
