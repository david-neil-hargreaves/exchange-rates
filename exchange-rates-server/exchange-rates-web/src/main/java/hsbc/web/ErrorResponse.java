package hsbc.web;

import java.util.Arrays;
import java.util.List;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Represents an error from the REST endpoints.
 */
@Data
public class ErrorResponse {

  private HttpStatus status;
  private String message;
  private List<String> errors;

  /**
   * Constructs an error response.
   * 
   * @param status The status.
   * @param message The message.
   * @param errors The error messages.
   */
  public ErrorResponse(HttpStatus status, String message, List<String> errors) {
    super();
    this.status = status;
    this.message = message;
    this.errors = errors;
  }

  /**
   * Constructs an error response.
   * 
   * @param status The status.
   * @param message The message.
   * @param error The error message.
   */
  public ErrorResponse(HttpStatus status, String message, String error) {
    super();
    this.status = status;
    this.message = message;
    errors = Arrays.asList(error);
  }

}
