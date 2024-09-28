package emv.backend.spring.fiesta.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Component
public class CorsConfig {

  @Value("${cors.allowed_origins}")
  private String CORS_ALLOWED_ORIGINS;

  @Value("${cors.allowed_methods}")
  private String CORS_ALLOWED_METHODS;

  @Value("${cors.allowed_headers}")
  private String CORS_ALLOWED_HEADERS;

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowedOrigins(List.of(CORS_ALLOWED_ORIGINS));
    config.setAllowedMethods(List.of(CORS_ALLOWED_METHODS));
    config.setAllowedHeaders(List.of(CORS_ALLOWED_HEADERS));
    config.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource =
        new UrlBasedCorsConfigurationSource();
    urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", config);

    return urlBasedCorsConfigurationSource;
  }
}
