package com.depromeet.sulsul.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class XssConfig implements WebMvcConfigurer {

  private final ObjectMapper objectMapper;

  @Bean
  public FilterRegistrationBean<XssEscapeServletFilter> filterRegistrationBean() {
    final FilterRegistrationBean<XssEscapeServletFilter> filterRegistration = new FilterRegistrationBean<>();
    filterRegistration.setFilter(new XssEscapeServletFilter());
    filterRegistration.setOrder(1);
    filterRegistration.addUrlPatterns("/*"); //filter를 거칠 url patterns
    return filterRegistration;
  }

  @Bean
  public MappingJackson2HttpMessageConverter jsonEscapeConverter() {
    ObjectMapper copy = objectMapper.copy();
    copy.getFactory().setCharacterEscapes(new HtmlCharacterEscapes());
    return new MappingJackson2HttpMessageConverter(copy);
  }
}
