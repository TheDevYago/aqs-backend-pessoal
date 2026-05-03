package com.arksgrupo.Arks_Requiem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // ERRO GENÉRICO
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception ex){
        Map<String, Object> error = new HashMap<>();

        error.put("timestamp", LocalDateTime.now());
        error.put("status", 500);
        error.put("erro", "Erro interno do servidor");
        error.put("mensagem", ex.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    // NÃO ENCONTRADO
    @ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<Map<String, Object>> handleNotFound(ResourceNotFoundException ex){
    Map<String, Object> error = new HashMap<>();

    error.put("timestamp", LocalDateTime.now());
    error.put("status", 404);
    error.put("erro", "Recurso não encontrado");
    error.put("mensagem", ex.getMessage());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

}

}
