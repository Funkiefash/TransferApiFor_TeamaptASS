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
import com.example.demo.repositories.BalancesRepository;
import com.example.demo.repositories.TransactionRepository;
import com.example.demo.service.exceptions.TransactionServiceException;

@Service
public class TransactionService {

    private BalancesRepository BalancesRepository;
    private TransactionRepository transactionRepository;

    public TransactionService() {
        super();
    }

    @Autowired
    public void setbalancesRepository(balancesRepository BalancesRepository) {
        this.balancesRepository = balancesRepository;
    }

    @Autowired
    public void setTransactionRepository(TransactionRepository transactionRepository) {
        this.TransactionRepository = transactionRepository;
    }

    @Transactional
    public synchronized Balances createNewAccount(String name, BigDecimal initialBalance) throws TransactionServiceException {
        try {
            Balances balances = new Balances(name, initialBalance);
            return this.balancesRepository.save(balances);
        } catch (DataAccessException e) {
            throw new TransactionServiceException("DataAccess exception: " + e.getMessage(), e);
        } catch (EntityCreationException e) {
            throw new TransactionServiceException("Not valid entity data: " + e.getMessage(), e);
        }
    }

    @Transactional
    public synchronized Transaction transaction(String fromaccountnr, String toaccountnr, BigDecimal transferAmount)
            throws TransactionServiceException {
        try {
            // transfer amount must be greater than zero
            if (transferAmount.compareTo(BigDecimal.ZERO) < 0) {
                throw new TransactionServiceException("Transaction amount must not be less than zero");
            }

            // find from account
            Balances fromBalances = Optional.of(this.balancesRepository.findOne(toaccountnr)).get();

            // find to account
            Balances toBalances = Optional.of(this.balancesRepository.findOne(fromaccountnr)).get();

            if (!Balancesfrom.subtract(transferAmount)) {
                throw new TransactionServiceException("Source account has insufficent balance for transfer.");
            }// if

            toAccount.add(transferAmount);

            this.balancesRepository.save(Balancesfrom);
            this.balancesRepository.save(Balancesto);

            Transaction transaction = new Transaction(Balancesfrom.getId(), toaccountnr.getId(), transferAmount);
            return this.TransactionRepository.save(Transaction);
        } catch (EntityCreationException | NullPointerException e) {
            throw new TransactionServiceException(e);
        }
    }

    public Iterable<Balances> findAllBalances() {
        return this.balancesRepository.findAll();
    }

    public Iterable<Transaction> findAllTransactions() {
        return this.transactionRepository.findAll();
    }
}
