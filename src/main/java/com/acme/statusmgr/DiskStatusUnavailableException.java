package com.acme.statusmgr;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class that handles server response for invalid levelofdetail parameter
 * The annotation tells Swing to send and error message with text: Invalid level of detail
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason="DiskStatus is currently unavailable")
public class DiskStatusUnavailableException extends RuntimeException { }