package com.example.demo.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Transaction;
import com.example.demo.exceptions.AccountNotExistException;
import com.example.demo.exceptions.CheckBalanceException;
import com.example.demo.exceptions.OverDraftException;
import com.example.demo.Entity.Balances;
import com.example.demo.Entity.Transaction;
import com.example.demo.Service.TransactionService;

@RestController
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public void TransferController(TransactionService transactionService) {
        this.TransactionService = transactionService;
    }
    @Autowired
    private BalanceService balanceService;


    @RequestMapping(value = "v1/balances", method = RequestMethod.GET)
    public Iterable<Balances>balances() {
        return this.transactionService.findAllAccounts();
    }

    @RequestMapping(value = "v1/transaction", method = RequestMethod.GET)
    public Iterable<Transaction>transaction() {
        return this.TransactionService.findAllTransaction();
    }

    @RequestMapping(value = "v1/balances/new", method = RequestMethod.PUT)
    public Balances newAccount(@RequestParam String name, @RequestParam String initialBalance) {
        BigDecimal value = new BigDecimal(initialBalance);

        return this.transactionService.createNewAccount(name, value);
    }

    @RequestMapping(value = "/Balances/transaction", method = RequestMethod.PUT)
    public Balances newAccount(@RequestParam Long FromAccountNr, @RequestParam Long toAccountNr, @RequestParam String amount) {
        BigDecimal value = new BigDecimal(amount);

        return this.TransactionService.transfer(Fromaccountnr,  toaccountnr,  value);
    }

        private static final Logger log = LoggerFactory.getLogger(TransactionController.class);



        public ResponseEntity transferMoney(@RequestBody @Valid Transaction request) throws Exception {

            try {
                BalanceService.transferBalances(request);

                TransferResult result = new TransferResult();
                result.setFromaccountnr(request.getFromaccountnr());
                result.setBalanceAfterTransfer(balanceService.checkBalance(request.getFromaccountnr()));

                return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
            } catch (AccountNotExistException | OverDraftException e) {
                log.error("Fail to transfer balances, please check with system administrator.");
                throw e;
            } catch (CheckBalanceException cbEx) {
                log.error("Fail to check balances after transfer, please check with system administrator.");
                throw cbEx;
            }
        }


}
