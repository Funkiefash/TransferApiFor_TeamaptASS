package com.example.demo.exceptions;

public class CheckBalanceException extends SystemException {

	public CheckBalanceException(String message, String errorCode) {
		super(message, errorCode);
	}

}
