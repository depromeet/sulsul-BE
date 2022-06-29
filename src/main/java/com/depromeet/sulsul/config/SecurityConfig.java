package com.depromeet.sulsul.config;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import com.depromeet.sulsul.oauth2.filter.JwtAuthenticationFilter;
import com.depromeet.sulsul.oauth2.handler.CustomAccessDeniedHandler;
import com.depromeet.sulsul.oauth2.handler.CustomAuthenticationEntryPoint;
import com.depromeet.sulsul.oauth2.handler.CustomLogoutHandler;
import com.depromeet.sulsul.oauth2.handler.CustomOAuth2SuccessHandler;
import com.depromeet.sulsul.oauth2.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Value("${client.url}")
  private String urlOfEnv;

  private final CustomOAuth2UserService customOAuth2UserService;
  private final CustomOAuth2SuccessHandler customOAuth2SuccessHandler;
  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final CustomAccessDeniedHandler customAccessDeniedHandler;
  private final CustomAuthenticationEntryPoint authenticationEntryPoint;
  private final CustomLogoutHandler customLogoutHandler;

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/h2-console/**",
        "/v3/api-docs",  "/configuration/ui",
        "/swagger-resources", "/configuration/security",
        "/swagger-ui.html", "/webjars/**","/swagger/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .httpBasic().disable()
        .csrf().disable()
        .formLogin().disable()
        .sessionManagement().sessionCreationPolicy(STATELESS)
        .and()
        .authorizeHttpRequests()
        .antMatchers("/swagger-resources/**","/swagger-ui/**").permitAll()
        .antMatchers( "/login/oauth2/code/**","/api/v1/token").permitAll()
        .antMatchers( "/oauth2/**").permitAll()
        .antMatchers("/guest/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .logout()
        .logoutSuccessUrl(urlOfEnv)
        .addLogoutHandler(customLogoutHandler)
        .and()
        .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler)
        .authenticationEntryPoint(authenticationEntryPoint)
        .and()
        .oauth2Login()
        .successHandler(customOAuth2SuccessHandler)
        .userInfoEndpoint()
        .userService(customOAuth2UserService);
    http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
  }
}
