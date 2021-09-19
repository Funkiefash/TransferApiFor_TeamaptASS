package com.example.demo.exceptions;


public class EntityCreationException extends RuntimeException {

    private static final long serialVersionUID = 5529313319738191205L;

    public EntityCreationException() {
        super();
    }

    public EntityCreationException(String message) {
        super(message);
    }

    public EntityCreationException(Throwable cause) {
        super(cause);
    }

    public EntityCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityCreationException(String message, Throwable cause, boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
