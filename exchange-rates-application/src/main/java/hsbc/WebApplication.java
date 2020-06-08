
package hsbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * The Spring Boot Exchange Rates application.
 */
@SpringBootApplication
@ComponentScan(basePackages = "hsbc")
public class WebApplication {

  private static final Logger LOGGER = LogManager.getLogger(WebApplication.class);

  /**
   * Runs the Spring Boot Exchange Rates application.
   * 
   * @param arguments the arguments - none are required.
   */
  public static void main(String[] arguments) {
    LOGGER.traceEntry();
    SpringApplication.run(WebApplication.class, arguments);
    LOGGER.traceExit();
  }

}
