package com.auth.jwt.security;

import com.auth.jwt.security.filter.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenFilterConfigure extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

  @Autowired
  private JwtAuthFilter jwtAuthFilter ;

  @Override
  public void configure(HttpSecurity http) throws Exception {
      http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
  }

}
