package hsbc.web;

import static hsbc.test.TestData.CONSTRAINT_VIOLATION_MESSAGE;
import static hsbc.test.TestData.MESSAGE;
import static hsbc.util.exception.ServiceException.MESSAGE_FOR_END_USER;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import hsbc.util.exception.ValidationException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

public class RestResponseEntityExceptionHandlerTest {

  private static final String ATTRIBUTE_RESPONSE_ENTITY = "Response entity";

  @Mock
  private HttpHeaders mockHttpHeaders;

  @Mock
  private WebRequest mockWebRequest;

  @Mock
  private ConstraintViolationException mockConstraintViolationException;

  @Mock
  private MethodArgumentNotValidException mockMethodArgumentNotValidException;

  @Mock
  private BindingResult mockBindingResult;

  @Mock
  private Set<ConstraintViolation<?>> mockConstraintViolations;

  @Mock
  private ConstraintViolation<?> mockConstraintViolation;

  private RestResponseEntityExceptionHandler restResponseEntityExceptionHandler =
      new RestResponseEntityExceptionHandler();

  @Before
  public void setup() {
    initMocks(this);
  }

  @Test
  public void testHandleConstraintViolationException() {
    Set<ConstraintViolation<?>> constraintViolations = new HashSet<>();
    constraintViolations.add(mockConstraintViolation);
    when(mockConstraintViolationException.getConstraintViolations())
        .thenReturn(constraintViolations);
    when(mockConstraintViolation.getMessage()).thenReturn(CONSTRAINT_VIOLATION_MESSAGE);
    List<String> expectedErrors = new ArrayList<String>();
    expectedErrors.add(CONSTRAINT_VIOLATION_MESSAGE);
    String message = null;
    ErrorResponse expectedErrorResponse =
        new ErrorResponse(HttpStatus.BAD_REQUEST, message, expectedErrors);
    ResponseEntity<Object> expectedResponseEntity =
        new ResponseEntity<>(expectedErrorResponse, HttpStatus.BAD_REQUEST);
    ResponseEntity<Object> actualResponseEntity = restResponseEntityExceptionHandler
        .handleConstraintViolationException(mockConstraintViolationException, mockWebRequest);
    assertEquals(ATTRIBUTE_RESPONSE_ENTITY, expectedResponseEntity, actualResponseEntity);
  }

  @Test
  public void testHandleValidationError() {
    ValidationException validationException = new ValidationException(MESSAGE);
    List<String> errors = new ArrayList<String>();
    ErrorResponse expectedErrorResponse =
        new ErrorResponse(HttpStatus.BAD_REQUEST, validationException.getMessage(), errors);
    ResponseEntity<Object> expectedResponseEntity =
        new ResponseEntity<>(expectedErrorResponse, HttpStatus.BAD_REQUEST);
    ResponseEntity<Object> actualResponseEntity = restResponseEntityExceptionHandler
        .handleValidationError(validationException, mockWebRequest);
    assertEquals(ATTRIBUTE_RESPONSE_ENTITY, expectedResponseEntity, actualResponseEntity);
  }

  @Test
  public void testHandleSystemError() {
    Exception exception = new RuntimeException(MESSAGE);
    List<String> errors = new ArrayList<String>();
    ErrorResponse expectedErrorResponse =
        new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, MESSAGE_FOR_END_USER, errors);
    ResponseEntity<Object> expectedResponseEntity =
        new ResponseEntity<>(expectedErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    ResponseEntity<Object> actualResponseEntity =
        restResponseEntityExceptionHandler.handleSystemError(exception, mockWebRequest);
    assertEquals(ATTRIBUTE_RESPONSE_ENTITY, expectedResponseEntity, actualResponseEntity);
  }

}
