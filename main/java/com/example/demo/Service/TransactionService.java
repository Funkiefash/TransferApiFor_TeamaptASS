package com.example.demo.Service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Entity.Balances;
import com.example.demo.Entity.Transaction;
import com.example.demo.exceptions.EntityCreationException;
import com.example.demo.repositories.IBalanceRepository;
import com.example.demo.repositories.TransactionRepository;
import com.example.demo.service.exceptions.TransactionServiceException;

@Service
public class TransactionService {

    private IBalanceRepository BalanceRepository;
    private TransactionRepository transactionRepository;

    public TransactionService() {
        super();
    }

    @Autowired
    public void setIBalanceRepository(IBalanceRepository ibalanceRepository) {
        this.ibalanceRepository = balanceRepository;
    }

    @Autowired
    public void setTransactionRepository(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public synchronized Balances createNewAccount(String name, BigDecimal initialBalance) throws TransactionServiceException {
        try {
            Account account = new Account(name, initialBalance);
            return this.ibalanceRepository.save(account);
        } catch (DataAccessException e) {
            throw new TransactionServiceException("DataAccess exception: " + e.getMessage(), e);
        } catch (EntityCreationException e) {
            throw new TransactionServiceException("Not valid entity data: " + e.getMessage(), e);
        }
    }

    @Transactional
    public synchronized Transaction transaction(Long fromaccountnr, Long toaccountnr, BigDecimal transferAmount)
            throws TransactionServiceException {
        try {
            // transfer amount must be greater than zero
            if (transferAmount.compareTo(BigDecimal.ZERO) < 0) {
                throw new TransactionServiceException("Transaction amount must not be less than zero");
            }

            // find from account
            Balances fromAccount = Optional.of(this.ibalanceRepository.findOne(toaccountnr)).get();

            // find to account
            Account toAccount = Optional.of(this.ibalanceRepository.findOne(fromaccountnr)).get();

            if (!fromAccount.subtract(transferAmount)) {
                throw new TransactionServiceException("Source account has insufficent balance for transfer.");
            }// if

            toAccount.add(transferAmount);

            this.IBalanceRepository.save(frombalance);
            this.IBalanceRepository.save(toAccount);

            Transaction transaction = new Transfer(fromBalance.getId(), toaccountnr.getId(), transferAmount);
            return this.transactionRepository.save(transfer);
        } catch (EntityCreationException | NullPointerException e) {
            throw new TransactionServiceException(e);
        }
    }

    public Iterable<Balances> findAllAccounts() {
        return this.ibalanceRepository.findAll();
    }

    public Iterable<Transaction> findAllTransactions() {
        return this.transactionRepository.findAll();
    }
}
