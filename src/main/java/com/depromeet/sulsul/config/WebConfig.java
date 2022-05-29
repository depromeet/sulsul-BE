package com.depromeet.sulsul.config;

import com.depromeet.sulsul.common.converter.StringToBeerTypeConverter;
import com.depromeet.sulsul.common.converter.StringToSortTypeConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addConverter(new StringToSortTypeConverter());
    registry.addConverter(new StringToBeerTypeConverter());
  }

  /**
   * 다른 출처의 자원을 공유할 수 있도록 설정하는 권한 체제
   *
   * @param registry
   */
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**") // CORS 를 적용할 URL 패턴
        .allowedOrigins("*") // 자원 공유를 허락할 Origin 지정; 모든 Origin 허락
        .allowedMethods( // 허용할 HTTP method 지정
            HttpMethod.GET.name(),
            HttpMethod.HEAD.name(),
            HttpMethod.POST.name(),
            HttpMethod.PUT.name(),
            HttpMethod.PATCH.name(),
            HttpMethod.DELETE.name()
        );
  }
}
