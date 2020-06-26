package hsbc.web;

import static hsbc.util.exception.ServiceException.MESSAGE_FOR_END_USER;

import hsbc.util.exception.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Handles exceptions from the REST endpoints.
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  private static final Logger LOGGER =
      LogManager.getLogger(RestResponseEntityExceptionHandler.class);

  public static final String FORMAT_ERROR_MESSAGE = "%s: %s";

  /**
   * Handles constraint violation errors.
   * 
   * @param exception The exception.
   * @param request The request.
   * @return Web error response.
   */
  @ExceptionHandler(value = {ConstraintViolationException.class})
  public ResponseEntity<Object> handleConstraintViolationException(
      ConstraintViolationException exception, WebRequest request) {
    LOGGER.traceEntry();
    List<String> errors = new ArrayList<String>();
    Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
    for (ConstraintViolation<?> constraintViolation : constraintViolations) {
      String errorMessage = constraintViolation.getMessage();
      errors.add(errorMessage);
    }
    String message = null;
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, message, errors);
    return LOGGER.traceExit(handleExceptionInternal(exception, errorResponse, new HttpHeaders(),
        errorResponse.getStatus(), request));
  }

  /**
   * Handles validation errors.
   * 
   * @param exception The exception.
   * @param request The request.
   * @return Web error response.
   */
  @ExceptionHandler(value = {ValidationException.class})
  public ResponseEntity<Object> handleValidationError(ValidationException exception,
      WebRequest request) {
    LOGGER.traceEntry();
    List<String> errors = new ArrayList<String>();
    ErrorResponse errorResponse =
        new ErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage(), errors);
    return LOGGER.traceExit(handleExceptionInternal(exception, errorResponse, new HttpHeaders(),
        errorResponse.getStatus(), request));
  }

  /**
   * Handles system errors.
   * 
   * @param exception The exception.
   * @param request The request.
   * @return Web error response.
   */
  @ExceptionHandler(value = {Exception.class})
  public ResponseEntity<Object> handleSystemError(Exception exception, WebRequest request) {
    LOGGER.traceEntry();
    LOGGER.error(exception.getMessage(), exception);
    List<String> errors = new ArrayList<String>();
    ErrorResponse errorResponse =
        new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, MESSAGE_FOR_END_USER, errors);
    return LOGGER.traceExit(handleExceptionInternal(exception, errorResponse, new HttpHeaders(),
        errorResponse.getStatus(), request));
  }

}
