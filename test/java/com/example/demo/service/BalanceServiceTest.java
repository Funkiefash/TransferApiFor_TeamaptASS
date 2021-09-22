package com.example.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

import com.example.demo.exceptions.BalancesNotExistException;
import com.example.demo.exceptions.OverDraftException;
import com.example.demo.exceptions.SystemException;
import com.example.demo.Entity.Balances;
import com.example.demo.Entity.Transaction;
import com.example.demo.Entity.TransactionRequest;
import com.example.demo.repositories.BalancesRepository;
import com.example.demo.Service.BalanceService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public class BalanceServiceTest {

    @Mock
    BalancesRepository balRepo;

    @InjectMocks
    BalanceService balService;

    @Test
    public void testRetrieveBalance() {
        when(balRepo.findByAccountnr("1234567898765432")).thenReturn(Optional.of(new Balances("USERA", BigDecimal.ONE)));

        assertEquals(BigDecimal.ONE, balService.retrieveBalances("USERA").getbalance());
    }

    @Test(expected = BalancesNotExistException.class)
    public void testRetrieveBalanceFromInvalidAccount() {
        when(balRepo.findByAccountnr("1234567898765432")).thenReturn(Optional.empty());

        balService.retrieveBalances("USERA");
    }

    @Test
    public void testTransferBalance() throws Exception, Exception, Exception {
        String fromaccountnr = "1234567898765432";
        String balanceFromTo = "USERB";
        BigDecimal amount = new BigDecimal(10);

        TransactionRequest request = new TransactionRequest();
        request.setfromaccountnr(fromaccountnr);
        request.settoaccountnr(balanceFromTo);
        request.setAmount(amount);

        Balances balFrom = new Balances(fromaccountnr, BigDecimal.TEN);
        Balances balTo = new Balances(fromaccountnr, BigDecimal.TEN);

        when(balRepo.getBalancesForUpdate(fromaccountnr)).thenReturn(Optional.of(balFrom));
        when(balRepo.getBalancesForUpdate(balanceFromTo)).thenReturn(Optional.of(balTo));

        balService.transferBalances(request);

        assertEquals(BigDecimal.ZERO, balFrom.getbalance());
        assertEquals(BigDecimal.TEN.add(BigDecimal.TEN), balTo.getbalance());
    }

    @Test(expected = OverDraftException.class)
    public void testOverdraftBalance() throws OverDraftException, BalancesNotExistException, SystemException {
        String fromaccountnr = "1234567898765432";
        String balanceFromTo = "USERB";
        BigDecimal amount = new BigDecimal(20);

        TransactionRequest request = new TransactionRequest();
        request.setfromaccountnr(fromaccountnr);
        request.settoaccountnr(balanceFromTo);
        request.setAmount(amount);

        Balances balFrom = new Balances(fromaccountnr, BigDecimal.TEN);
        Balances balTo = new Balances(fromaccountnr, BigDecimal.TEN);

        when(balRepo.getBalancesForUpdate(fromaccountnr)).thenReturn(Optional.of(balFrom));
        when(balRepo.getBalancesForUpdate(balanceFromTo)).thenReturn(Optional.of(balTo));

        balService.transferBalances(request);
    }
}
