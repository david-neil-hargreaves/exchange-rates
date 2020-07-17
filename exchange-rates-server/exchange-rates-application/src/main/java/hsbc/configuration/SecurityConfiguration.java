
package hsbc.configuration;

import java.util.Arrays;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Security configuration for the Exchange Rates application.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private static final String AUTHORISE_REQUESTS_PATH = "/**";
  private static final String ACCESS_DENIED_PAGE = "/access-denied";
  private static final List<String> ALLOWED_ORIGINS = Arrays.asList("http://localhost:4200");
  private static final List<String> ALLOWED_METHODS =
      Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS");
  private static final List<String> ALLOWED_HEADERS =
      Arrays.asList("Authorization", "Cache-Control", "Content-Type");
  private static final List<String> EXPOSED_HEADERS =
      Arrays.asList("custom-header1", "custom-header2");
  private static final String CORS_CONFIGURATION_PATH = "/**";

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().and().csrf().disable().authorizeRequests().antMatchers(AUTHORISE_REQUESTS_PATH)
        .permitAll().and().exceptionHandling().accessDeniedPage(ACCESS_DENIED_PAGE);
  }

  /**
   * Sets up the application CORS (Cross Origin Resource Sharing) security configuration.
   * 
   * @return application CORS (Cross Origin Resource Sharing) security configuration.
   */
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    // TODO Statics!!! Here and above.
    configuration.setAllowedOrigins(ALLOWED_ORIGINS);
    configuration.setAllowCredentials(true);
    configuration.setAllowedMethods(ALLOWED_METHODS);
    configuration.setAllowedHeaders(ALLOWED_HEADERS);
    configuration.setExposedHeaders(EXPOSED_HEADERS);
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration(CORS_CONFIGURATION_PATH, configuration);
    return source;
  }
}
