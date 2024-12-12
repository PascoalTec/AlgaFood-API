package com.algaworks.algafood.domain.exception;

public class StorageException extends RuntimeException {
    
    public static final long serialVersionUID = 1L;

    public StorageException (String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
