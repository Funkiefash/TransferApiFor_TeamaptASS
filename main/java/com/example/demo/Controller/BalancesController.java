

/*
 * Copyright (c) 2021.
 * Debbiefasipe.
 */

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
@RequestMapping("/v1/balances")
@Api(tags = { "Accounts Controller" }, description = "Provide APIs for account related operation")
public class BalancesController {

    @Autowired
    private BalanceService balanceService;
    private String accountnr;

    @GetMapping("/{accountId}/balances")
    @ApiOperation(value = "Get account balance by id", response = Balances.class, produces = "application/json")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Account not found with ID")})
    public Balances getbalance(

            @ApiParam(value = "ID related to the account", required = true) @PathVariable String accountnr)
    {
        return balanceService.retrieveBalances(accountnr);
    }
}
