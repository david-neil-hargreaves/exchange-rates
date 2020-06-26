package hsbc.web;

import static hsbc.test.TestData.ERROR;
import static hsbc.test.TestData.ERRORS;
import static hsbc.test.TestData.MESSAGE;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.http.HttpStatus;

public class ErrorResponseTest {

  private static final String ATTRIBUTE_HTTP_STATUS = "HTTP status";
  private static final String ATTRIBUTE_MESSAGE = "Message";
  private static final String ATTRIBUTE_ERRORS = "Errors";

  @Test
  public void testConstructor() {
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.OK, MESSAGE, ERRORS);
    assertEquals(ATTRIBUTE_HTTP_STATUS, HttpStatus.OK, errorResponse.getStatus());
    assertEquals(ATTRIBUTE_MESSAGE, MESSAGE, errorResponse.getMessage());
    assertEquals(ATTRIBUTE_ERRORS, ERRORS, errorResponse.getErrors());
  }

  @Test
  public void testConstructorOverloaded() {
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.OK, MESSAGE, ERROR);
    assertEquals(ATTRIBUTE_HTTP_STATUS, HttpStatus.OK, errorResponse.getStatus());
    assertEquals(ATTRIBUTE_MESSAGE, MESSAGE, errorResponse.getMessage());
    assertEquals(ATTRIBUTE_ERRORS, ERRORS, errorResponse.getErrors());
  }

}
