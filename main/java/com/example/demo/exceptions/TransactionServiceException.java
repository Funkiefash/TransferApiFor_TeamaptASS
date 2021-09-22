package com.example.demo.service.exceptions;

public class TransactionServiceException extends RuntimeException {

    private static final long serialVersionUID = 1668584501354958168L;

    public TransactionServiceException() {
        super();
    }

    public TransactionServiceException(String message) {
        super(message);
    }

    public TransactionServiceException(Throwable cause) {
        super(cause);
    }

    public TransactionServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionServiceException(String message, Throwable cause, boolean enableSuppression,
                                    boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
