package com.example.demo.Service.transfer;

import com.example.demo.Entity.TransactionResult;
import com.example.demo.Entity.TransactionRequest;

/**
 * Balances transfer service.
 */
public interface TransactionService {

    /**
     * Transfer funds from one account to another
     *
     * @param transferRequest request
     * @return transfer Result
     */
    TransactionResult Transaction(TransactionRequest transactionRequest);
}
