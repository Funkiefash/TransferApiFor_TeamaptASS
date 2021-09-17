package com.example.demo.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Balances;
import com.example.demo.Service.BalanceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/v1/Balances")
@Api(tags = { "Balances Controller" }, description = "Provide APIs for account balance related operation")
public class BalancesController {

    @Autowired
    private BalanceService balanceService;

    @GetMapping("/{accountNr}/balance")
    @ApiOperation(value = "Get account balance by account number", response = Balances.class, produces = "application/json")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Account not found with ID")})
    public Account getBalance(
            @ApiParam(value = "Account number related to the account", required = true) @PathVariable Long accountNr) {
        return BalanceService.retrieveBalances(accountNr);
    }
}
