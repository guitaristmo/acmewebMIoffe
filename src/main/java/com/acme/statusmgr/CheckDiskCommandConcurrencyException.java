package com.acme.statusmgr;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class that handles requests for a disk check command
 * when one is already running.
 * The annotation tells Swing to send and error message with text: Check command is already running
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Check command is already running")
public class CheckDiskCommandConcurrencyException extends RuntimeException { }
