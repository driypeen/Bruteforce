package com.driypeen.Bruteforce.exception;

import org.springframework.http.HttpStatus;

public class DTOMappingException extends ApiException{
    public DTOMappingException() {
        super("Error in DTO mapping");
    }

    public DTOMappingException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NO_CONTENT;
    }
}
