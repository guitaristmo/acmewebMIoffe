package com.acme.statusmgr;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class that handles DiskStatus response for null name parameter requests
 * The annotation tells Swing to send and error message with text
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Name parameter may not be null for DiskStatus request")
public class InvalidNameException extends RuntimeException {
}
