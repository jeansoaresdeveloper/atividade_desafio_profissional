package com.ativade.crud.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RestException extends Exception {

    private final HttpStatus status;

    public RestException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
