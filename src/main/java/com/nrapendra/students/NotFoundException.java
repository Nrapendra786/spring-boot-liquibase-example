package com.nrapendra.students;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundException extends ResponseStatusException {

    private static final String NOT_FOUND = "not found";

    public NotFoundException(HttpStatusCode status, String message) {
        super(HttpStatus.NOT_FOUND, NOT_FOUND);
    }
}
