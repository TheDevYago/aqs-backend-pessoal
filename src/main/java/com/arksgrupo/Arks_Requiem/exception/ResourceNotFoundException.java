package com.arksgrupo.Arks_Requiem.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String mensagem){
        super(mensagem);
    }
}
