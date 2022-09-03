package com.br.senac.planey.application.exception;

import com.br.senac.planey.domain.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value
            = {BusinessException.class})
    protected ResponseEntity<Object> handleBusinessException(RuntimeException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .mensagem(ex.getMessage())
                .dtHrOcorrencia(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value
            = {Exception.class})
    protected ResponseEntity<Object> handleAllException(RuntimeException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .mensagem(ex.getMessage())
                .dtHrOcorrencia(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
