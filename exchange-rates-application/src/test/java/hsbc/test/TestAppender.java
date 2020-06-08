
package hsbc.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;

@Plugin(name = "TestAppender", category = "Core", elementType = "appender", printObject = true)
public class TestAppender extends AbstractAppender {

  private static boolean IGNORE_EXCEPTIONS = false;
  private static Property[] PROPERTIES = null;

  private List<String> messages = new ArrayList<String>();

  protected TestAppender(String name, Filter filter, Layout<? extends Serializable> layout,
      boolean ignoreExceptions, Property[] properties) {
    super(name, filter, layout, ignoreExceptions, properties);
  }

  @Override
  public void append(LogEvent event) {
    getMessages().add(event.getMessage().toString());
  }

  @PluginFactory
  public static TestAppender createAppender(@PluginAttribute("name") String name,
      @PluginElement("Layout") Layout<? extends Serializable> layout,
      @PluginElement("Filter") final Filter filter,
      @PluginAttribute("otherAttribute") String otherAttribute) {
    if (name == null) {
      LOGGER.error("No name provided for TestAppender");
      return null;
    }
    if (layout == null) {
      layout = PatternLayout.createDefaultLayout();
    }
    return new TestAppender(name, filter, layout, IGNORE_EXCEPTIONS, PROPERTIES);
  }

  public List<String> getMessages() {
    return messages;
  }

  public void setMessages(List<String> messages) {
    this.messages = messages;
  }
}
