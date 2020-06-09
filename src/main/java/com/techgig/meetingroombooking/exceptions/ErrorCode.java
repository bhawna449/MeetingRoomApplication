package com.techgig.meetingroombooking.exceptions;

import org.springframework.http.HttpStatus;

public interface ErrorCode {

    HttpStatus getHttpStatus();

    /**
     * Gets the name of code.
     *
     * @return the error code identifier
     */

    String getIdentifier();
}
