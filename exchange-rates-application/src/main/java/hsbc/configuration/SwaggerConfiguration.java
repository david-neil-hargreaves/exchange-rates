package hsbc.configuration;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger configuration for the Exchange Rates application.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

  /**
   * Sets up the Swagger Web interface.
   * 
   * @return link discoverers
   */
  @Bean
  public LinkDiscoverers discoverers() {
    List<LinkDiscoverer> plugins = new ArrayList<>();
    plugins.add(new CollectionJsonLinkDiscoverer());
    return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
  }

}
