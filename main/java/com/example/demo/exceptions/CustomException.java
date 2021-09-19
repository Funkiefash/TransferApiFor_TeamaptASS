package com.example.demo.exceptions;

public class CustomException extends Exception{
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new custom exception.
     *
     * @param msg the msg
     */
    public CustomException(String msg) {
        super(msg);
    }

    /**
     * Instantiates a new custom exception.
     *
     * @param msg the msg
     * @param cause the cause
     */
    public CustomException(String msg, Throwable cause) {
        super(msg, cause);
    }


}
