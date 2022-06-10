package com.depromeet.sulsul.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import springfox.documentation.oas.web.OpenApiTransformationContext;
import springfox.documentation.oas.web.WebMvcOpenApiTransformationFilter;
import springfox.documentation.spi.DocumentationType;

@Component
public class Workaround implements WebMvcOpenApiTransformationFilter {

  @Value("${swagger.url}")
  private String url;

  @Value("${spring.profiles.active}")
  private String profile;

  @Override
  public OpenAPI transform(OpenApiTransformationContext<HttpServletRequest> context) {
    final OpenAPI openApi = context.getSpecification();
    openApi.setServers(List.of(getServer(url, profile)));
    return openApi;
  }

  @Override
  public boolean supports(DocumentationType documentationType) {
    return documentationType.equals(DocumentationType.OAS_30);
  }

  private Server getServer(String url, String desc) {
    Server server = new Server();
    server.url(url);
    server.description(desc);
    return server;
  }
}
