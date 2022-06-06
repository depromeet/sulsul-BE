package com.depromeet.sulsul.config;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Server;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi
public class SwaggerConfig {

  @Value("${swagger.url}")
  private String url;

  @Value("${swagger.desc}")
  private String desc;

  @Value("${spring.profiles.active}")
  private String profile;

  @Bean
  public Docket restApi() {
    return new Docket(DocumentationType.OAS_30)
        .servers(getServer(profile, url, desc))
        .useDefaultResponseMessages(false)
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.depromeet.sulsul.domain"))
        .paths(PathSelectors.ant("/api/**"))
        .build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("sulsul api info")
        .description("sulsul API")
        .version("1.0.0")
        .build();
  }

  private Server getServer(String profile, String url, String desc) {
    return new Server(profile, url, desc, Collections.emptyList(), Collections.emptyList());
  }
}
