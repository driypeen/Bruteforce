package com.driypeen.Bruteforce.exception;

import org.springframework.http.HttpStatus;

public class NotFoundElementException extends ApiException{

    public NotFoundElementException(){
        super("Element not found");
    }
    public NotFoundElementException(String message){
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
