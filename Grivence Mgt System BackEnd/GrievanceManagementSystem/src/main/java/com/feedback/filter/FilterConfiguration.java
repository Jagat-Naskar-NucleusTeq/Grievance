package com.feedback.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.feedback.serviceImplementation.AuthenticationServiceImpl;

/**
 * Configuration class for setting up filters.
 */
@Configuration
public class FilterConfiguration {

  /**
   * AuthenticationService object.
   */
  private final AuthenticationServiceImpl authenticationServiceImpl;

  /**
   * Constructor for FilterConfiguration.
   *
   * @param authenticationServiceImpll The AuthenticationService to be injected.
   *
   */
  @Autowired
  public FilterConfiguration(
      final AuthenticationServiceImpl authenticationServiceImpll) {
    this.authenticationServiceImpl = authenticationServiceImpll;
  }

  /**
   * Bean definition for registering AuthenticationFilter.
   *
   * @return FilterRegistrationBean instance for AuthenticationFilter.
   */
  @Bean
  public FilterRegistrationBean<AuthenticationFilter> registrationBean() {
    FilterRegistrationBean<AuthenticationFilter> regBean
          = new FilterRegistrationBean<AuthenticationFilter>();
    regBean.setFilter(new AuthenticationFilter(authenticationServiceImpl));
    regBean.addUrlPatterns("/api/dept/addDept");
    return regBean;
  }
}
