package com.example.demo.exceptions;

class DuplicateTransferException extends RuntimeException {

   DuplicateTransferException(Long TransactionReference) {
        super("Duplicate Transfer,Please try again " + TransactionReference);
    }
}