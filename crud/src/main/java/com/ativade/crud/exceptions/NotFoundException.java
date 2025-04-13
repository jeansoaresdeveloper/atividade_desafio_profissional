package com.ativade.crud.exceptions;


import org.springframework.http.HttpStatus;

public class NotFoundException extends RestException {

    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
