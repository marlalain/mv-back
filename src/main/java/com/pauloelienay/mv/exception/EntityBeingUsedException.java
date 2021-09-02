package com.pauloelienay.mv.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EntityBeingUsedException extends RuntimeException {
    public EntityBeingUsedException(String message) {
        super(message);
    }
}
