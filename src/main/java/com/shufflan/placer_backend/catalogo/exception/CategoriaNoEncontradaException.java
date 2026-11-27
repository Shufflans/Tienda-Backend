package com.shufflan.placer_backend.catalogo.exception;

public class CategoriaNoEncontradaException extends RuntimeException {
    public CategoriaNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}
