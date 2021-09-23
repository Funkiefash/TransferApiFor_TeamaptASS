package com.example.demo.Controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.TransactionResult;
import com.example.demo.exceptions.BalancesNotExistException;
import com.example.demo.exceptions.CheckBalanceException;
import com.example.demo.exceptions.OverDraftException;
import com.example.demo.Entity.TransactionRequest;
import com.example.demo.Service.BalanceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1/transaction")
@Api(tags = {"Transaction Controller"}, description = "Provide APIs for transaction related operation")
public class TransactionController {

    private static final Logger log = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private BalanceService balanceService;

    @PostMapping(consumes = { "application/json" })
    @ApiOperation(value = "API to create transaction", response = TransactionResult.class, produces = "application/json")
    public ResponseEntity transferMoney(@RequestBody @Valid TransactionRequest request) throws Exception {

        try {
            balanceService.transferBalances(request);

            TransactionResult result = new TransactionResult();
            result.setfromaccountnr(request.getfromaccountnr());
            result.setBalanceAfterTransfer(balanceService.checkBalance(request.getfromaccountnr()));

            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        } catch (BalancesNotExistException | OverDraftException e) {
            log.error("Fail to transfer balances, please check with system administrator.");
            throw e;
        } catch (CheckBalanceException cbEx) {
            log.error("Fail to check balances after transfer, please check with system administrator.");
            throw cbEx;
        }
    }
}
