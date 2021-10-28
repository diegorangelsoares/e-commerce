package br.com.diego.exception;

public class CambioNotFoundException extends RuntimeException {

    public CambioNotFoundException(String message) {
        super(message);
    }
}
