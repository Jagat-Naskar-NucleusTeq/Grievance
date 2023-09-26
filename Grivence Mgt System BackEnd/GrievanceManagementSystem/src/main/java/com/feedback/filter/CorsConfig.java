package com.feedback.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CorsConfig class.
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

  /**
   * addCorsMappings.
   *
   * @param registry
   *
   */
  @Override
  public void addCorsMappings(final CorsRegistry registry) {
    registry.addMapping("/**")
                .allowedOrigins("*") // Allow requests from any origin
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*"); // Allow any headers
  }
}
