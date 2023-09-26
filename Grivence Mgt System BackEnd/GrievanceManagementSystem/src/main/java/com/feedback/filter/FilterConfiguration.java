package com.feedback.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.feedback.serviceImplementation.AuthenticationService;

/**
 * Configuration class for setting up filters.
 */
@Configuration
public class FilterConfiguration {

  /**
   * AuthenticationService object.
   */
  private final AuthenticationService authenticationService;

  /**
   * Constructor for FilterConfiguration.
   *
   * @param authenticationService The AuthenticationService to be injected.
   *
   */
  @Autowired
  public FilterConfiguration(
      final AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
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
    regBean.setFilter(new AuthenticationFilter(authenticationService));
    regBean.addUrlPatterns("/api/dept/addDept");
    // Uncomment the following line if needed for additional URL patterns.
    // regBean.addUrlPatterns("/api/users/addUser");
    System.out.println("in config filter.");
    return regBean;
  }
}
