package com.depromeet.sulsul.config;

import com.depromeet.sulsul.common.converter.StringToBeerTypeConverter;
import com.depromeet.sulsul.common.converter.StringToSortTypeConverter;
import com.depromeet.sulsul.common.interceptor.AuthInterceptor;
import com.depromeet.sulsul.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

  private final MemberRepository memberRepository;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(authInterceptor())
        .order(1)
        .addPathPatterns("/api/**")
        .excludePathPatterns("/oauth2/authorization/**", "/guest/**");
  }

  @Bean
  public AuthInterceptor authInterceptor() {
    return new AuthInterceptor(memberRepository);
  }

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
        .allowedOrigins("http://localhost:3000", "http://localhost", "https://beerair.ml/") // 자원 공유를 허락할 Origin 지정; 모든 Origin 허락
        .allowedMethods( // 허용할 HTTP method 지정
            HttpMethod.GET.name(),
            HttpMethod.HEAD.name(),
            HttpMethod.POST.name(),
            HttpMethod.PUT.name(),
            HttpMethod.PATCH.name(),
            HttpMethod.DELETE.name())
        .allowCredentials(true);
  }
}
