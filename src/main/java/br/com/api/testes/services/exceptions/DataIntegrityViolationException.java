package br.com.api.testes.services.exceptions;

public class DataIntegrityViolationException extends RuntimeException{

    public DataIntegrityViolationException(String message) {
        super(message);
    }
}
