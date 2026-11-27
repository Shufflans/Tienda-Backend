package com.shufflan.placer_backend.catalogo.exception;

public class CategoriaYaExisteException extends RuntimeException {
    public CategoriaYaExisteException(String mensaje) {
        super(mensaje);
    }
}
