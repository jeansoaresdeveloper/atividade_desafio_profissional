package com.ativade.crud.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
public class ResponseException {

    @Builder.Default
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final String message;
    private final HttpStatus status;

    public static ResponseException of(final RestException exception) {
        return build(exception.getMessage(), exception.getStatus());
    }

    public static ResponseException of(final String message, final HttpStatus status) {
        return build(message, status);
    }

    private static ResponseException build(final String message, final HttpStatus status) {
        return ResponseException.builder()
                .message(message)
                .status(status)
                .build();
    }
}
