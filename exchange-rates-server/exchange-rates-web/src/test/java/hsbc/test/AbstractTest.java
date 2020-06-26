
package hsbc.test;

import org.junit.Rule;
import org.junit.rules.ExpectedException;

public abstract class AbstractTest {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

}
