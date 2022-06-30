package com.depromeet.sulsul.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

  @Bean
  public RestTemplate restTemplate() {
    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
    requestFactory.setHttpClient(httpClient());
    return new RestTemplate(requestFactory);
  }

  @Bean
  public HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory() {
    HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
    httpComponentsClientHttpRequestFactory.setConnectTimeout(2000);
    httpComponentsClientHttpRequestFactory.setReadTimeout(5000);
    return httpComponentsClientHttpRequestFactory;
  }

  @Bean
  public CloseableHttpClient httpClient() {
    return HttpClientBuilder.create()
        .setMaxConnTotal(100)
        .setMaxConnPerRoute(5)
        .build();
  }
}
