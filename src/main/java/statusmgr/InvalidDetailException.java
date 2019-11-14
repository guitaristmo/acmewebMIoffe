package statusmgr;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class that handles server response for invalid detail parameters
 * The annotation tells Swing to send and error message with text: Invalid details options
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Invalid details option")
public class InvalidDetailException extends RuntimeException { }