package com.driypeen.Bruteforce.exception;

import org.springframework.http.HttpStatus;

public class TokenException extends ApiException{
    public TokenException() {
        super("Unknown error during JWT-token processing.");
    }

    public TokenException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.valueOf(500);
    }
}
