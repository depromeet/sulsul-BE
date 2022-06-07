package com.depromeet.sulsul.config;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

@Configuration
public class AccessControlAllowFilter implements Filter {

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
      throws IOException, ServletException {

    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) res;

    response.setHeader("Access-Control-Allow-Origin", "localhost:3000");
    response.setHeader("Access-Control-Allow-Methods", "*");
    response.setHeader("Access-Control-Expose-Headers", "jwt");
    response.setHeader("Access-Control-Allow-Credentials", "true");

    if (request.getMethod().equals(HttpMethod.OPTIONS.name())) {
      response.setStatus(HttpStatus.OK.value());
    } else {
      chain.doFilter(req, res);
    }
  }
}

