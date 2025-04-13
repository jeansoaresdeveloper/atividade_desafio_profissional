package com.ativade.crud.config;

import com.ativade.crud.enums.Classe;
import com.ativade.crud.enums.TipoItem;
import com.ativade.crud.exceptions.ResponseException;
import com.ativade.crud.exceptions.RestException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>("Ocorreu um erro inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RestException.class)
    public ResponseEntity<ResponseException> handleRestException(RestException exception) {
        final ResponseException response = ResponseException.of(exception);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseException> handleValidationExceptions(MethodArgumentNotValidException exception) {

        final StringBuilder message = new StringBuilder();

        exception.getBindingResult().getAllErrors().forEach(error ->
                message.append(error.getDefaultMessage()).append(System.lineSeparator())
        );

        final ResponseException response = ResponseException.of(message.toString(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseException> enumException(HttpMessageNotReadableException exception) {

        Throwable cause = exception.getCause();

        if (!(cause instanceof InvalidFormatException)) {
            return buildException(exception.getMessage());
        }

        final Class<?> targetType = ((InvalidFormatException) cause).getTargetType();

        if (!targetType.isEnum()) {
            return buildException(exception.getMessage());
        }

        final Object value = ((InvalidFormatException) cause).getValue();
        final String className = targetType.getSimpleName();

        final Class<? extends Enum<?>> enumClass = enumByClassName().get(className);
        final String enumValues = Arrays.stream(enumClass.getFields()).map(Field::getName).collect(Collectors.joining(", "));

        final String message = String.format("%s %s inválido, Valores válidos: %s", className, value, enumValues);

        final ResponseException response = ResponseException.of(message, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response, response.getStatus());

    }

    private ResponseEntity<ResponseException> buildException(final String message) {
        log.error(message);
        final ResponseException response = ResponseException.of("Ocorreu um erro inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, response.getStatus());
    }

    private Map<String, Class<? extends Enum<?>>> enumByClassName() {

        final Map<String, Class<? extends Enum<?>>> enumByCause = new HashMap<>();

        enumByCause.put("Classe", Classe.class);
        enumByCause.put("TipoItem", TipoItem.class);

        return enumByCause;
    }

}
