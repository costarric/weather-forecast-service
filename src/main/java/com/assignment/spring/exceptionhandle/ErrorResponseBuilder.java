package com.assignment.spring.exceptionhandle;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErrorResponseBuilder {

    private final ErrorResponse errorResponse;

    public ErrorResponseBuilder() {
        this.errorResponse = new ErrorResponse();
    }

    public ErrorResponseBuilder errorKey(String errorKey) {
        this.errorResponse.setErrorKey(errorKey);
        return this;
    }

    public ErrorResponseBuilder message(String message) {
        this.errorResponse.setMessage(message);
        return this;
    }

    public ResponseEntity<Object> buildResponseEntity(HttpStatus httpStatus) {
        this.errorResponse.setStatus(httpStatus.value());
        return new ResponseEntity<>(this.errorResponse, httpStatus);
    }

}
