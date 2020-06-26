
package hsbc.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Configuration for the Exchange Rates application.
 */
@Configuration
@PropertySource("classpath:exchange-rates.properties")
public class EnvironmentConfiguration {

}
