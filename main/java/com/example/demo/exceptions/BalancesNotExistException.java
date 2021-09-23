package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;

public class BalancesNotExistException extends BusinessException {

    public BalancesNotExistException(String message, String errorCode) {

        super(message, errorCode);
    }

    public BalancesNotExistException(String message, String errorCode, HttpStatus httpStatus) {
        super(message, errorCode, httpStatus);
    }

}
