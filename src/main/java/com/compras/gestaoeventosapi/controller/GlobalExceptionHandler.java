package com.compras.gestaoeventosapi.controller;

import com.compras.gestaoeventosapi.model.response.ErroResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErroResponse> handleResponseStatusException(ResponseStatusException ex) {
        int statusCode = ex.getStatusCode().value();
        String mensagem = ex.getReason();

        var erro = new ErroResponse(statusCode, mensagem);
        return ResponseEntity.status(statusCode).body(erro);
    }
}
