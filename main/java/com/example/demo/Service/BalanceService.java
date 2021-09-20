package com.example.demo.Service;

import java.math.BigDecimal;
import java.net.SocketTimeoutException;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.example.demo.Constant.ErrorCode;
import com.example.demo.exceptions.AccountNotExistException;
import com.example.demo.exceptions.CheckBalanceException;
import com.example.demo.exceptions.OverDraftException;
import com.example.demo.exceptions.SystemException;
import com.example.demo.Entity.Balances;
import com.example.demo.Entity.TransactionRequest;
import com.example.demo.repositories.BalancesRepository;

@Service
public class BalanceService {

    private static final Logger log = LoggerFactory.getLogger(BalanceService.class);

    @Autowired
    private BalancesRepository  balancesRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${endpoint.BalancesBalance}")
    private String retrieveAccountBalanceUrl;

    public Balances retrieveBalances(String accountnr) {
        Balances Balances = balancesRepository.findByaccountnr(accountnr)
                .orElseThrow(() -> new AccountNotExistException("Account with id:" + accountnr + " does not exist.", ErrorCode.Balances_ERROR, HttpStatus.NOT_FOUND));

        return Balances;
    }

    @Transactional
    public void transferBalances(TransactionRequest transfer) throws OverDraftException, AccountNotExistException, SystemException {
        Balances BalancesFrom = balancesRepository.getBalancesForUpdate(transfer.getfromaccountnr())
                .orElseThrow(() -> new AccountNotExistException("Account with id:" + transfer.getfromaccountnr() + " does not exist.", ErrorCode.Balances_ERROR));

        Balances BalancesTo = balancesRepository.getBalancesForUpdate(transfer.gettoaccountnr())
                .orElseThrow(() -> new AccountNotExistException("Account with id:" + transfer.getfromaccountnr() + " does not exist.", ErrorCode.Balances_ERROR));

        if(BalancesFrom.getbalance().compareTo(transfer.getAmount()) < 0) {
            throw new OverDraftException("Account with id:" + BalancesFrom.getaccountnr() + " does not have enough balance to transfer.", ErrorCode.Balances_ERROR);
        }

        BalancesFrom.setbalance(BalancesFrom.getbalance().subtract(transfer.getAmount()));
        BalancesTo.setbalance(BalancesTo.getbalance().add(transfer.getAmount()));
    }

    public BigDecimal checkBalance(String accountnr) throws SystemException {

        try {
            String url = retrieveBalancesbalanceUrl.replace("{id}", accountnr.toString());

            log.info("checking balance from "+url);

            ResponseEntity<Balances> balanceCheckResult = restTemplate.getForEntity(url, Balances.class);

            if(balanceCheckResult.getStatusCode().is2xxSuccessful()) {
                if(balanceCheckResult.hasBody()) {
                    return balanceCheckResult.getBody().getbalance();
                }
            }
        } catch (ResourceAccessException ex) {
            final String errorMessage = "Encounter timeout error, please check with system administrator.";

            if(ex.getCause() instanceof SocketTimeoutException) {
                throw new CheckbalanceException(errorMessage, ErrorCode.TIMEOUT_ERROR);
            }
        }
        // for any other fail cases
        throw new SystemException("Encounter internal server error, please check with system administrator.", ErrorCode.SYSTEM_ERROR);
    }
}
