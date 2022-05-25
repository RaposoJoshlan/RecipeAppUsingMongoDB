package com.recipe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created on 28/04/2022
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
        this.setStackTrace(new StackTraceElement[0]);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
        this.setStackTrace(new StackTraceElement[0]);
    }
}
