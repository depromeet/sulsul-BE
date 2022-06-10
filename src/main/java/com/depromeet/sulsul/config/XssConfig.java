package com.depromeet.sulsul.config;

import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class XssConfig implements WebMvcConfigurer {

  @Bean
  public FilterRegistrationBean<XssEscapeServletFilter> filterRegistrationBean() {
    final FilterRegistrationBean<XssEscapeServletFilter> filterRegistration = new FilterRegistrationBean<>();
    filterRegistration.setFilter(new XssEscapeServletFilter());
    filterRegistration.setOrder(1);
    filterRegistration.addUrlPatterns("/*"); //filter를 거칠 url patterns
    return filterRegistration;
  }
}
