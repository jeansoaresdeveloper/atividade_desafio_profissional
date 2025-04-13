package com.ativade.crud.exceptions;

import org.springframework.http.HttpStatus;

public class PersonagemException extends RestException {

    public PersonagemException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

}
