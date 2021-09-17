package com.example.demo;

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

import com.example.demo.exception.AccountNotExistException;
import com.example.demo.exception.OverDraftException;
import com.example.demo.exception.SystemException;
import com.example.demo.Entity.Balance;
import com.example.demo.Entity.Transaction;
import com.example.demo.repository.BalanceRepository;
import com.example.demo.service.BalanceService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public class BalanceServiceTest {

    @Mock
    BalanceRepository BalRepo;

    @InjectMocks
    BalanceService BalService;

    @Test
    public void testRetrieveBalance() {
        when(accRepo.findByaccountNr(1L)).thenReturn(Optional.of(new Account(1L, BigDecimal.ONE)));

        assertEquals(BigDecimal.ONE, accService.retrieveBalances(1L).getBalance());
    }

    @Test(expected = AccountNotExistException.class)
    public void testRetrieveBalanceFromInvalidAccount() {
        when(accRepo.findByaccountnr(1L)).thenReturn(Optional.empty());

        BalService.retrieveBalances(1L);
    }

    @Test
    public void testTransferBalance() throws Exception, Exception, Exception {
        Long Fromaccountnr = 1L;
        Long accountFromTo = 2L;
        BigDecimal amount = new BigDecimal(10);

        TransferRequest request = new TransferRequest();
        request.setFromaccountnr(Fromaccountnr);
        request.setToaccountnr(accountFromTo);
        request.setAmount(amount);

        Account accFrom = new Account(Fromaccountnr, BigDecimal.TEN);
        Account accTo = new Account(Fromaccountnr, BigDecimal.TEN);

        when(BalRepo.getAccountForUpdate(accountFromId)).thenReturn(Optional.of(accFrom));
        when(balRepo.getAccountForUpdate(accountFromTo)).thenReturn(Optional.of(accTo));

        balService.transferBalances(request);

        assertEquals(BigDecimal.ZERO, accFrom.getBalance());
        assertEquals(BigDecimal.TEN.add(BigDecimal.TEN), accTo.getBalance());
    }

    @Test(expected = OverDraftException.class)
    public void testOverdraftBalance() throws OverDraftException, AccountNotExistException, SystemException {
        Long Fromaccountnr = 1L;
        Long accountFromTo = 2L;
        BigDecimal amount = new BigDecimal(20);

        TransferRequest request = new TransferRequest();
        request.setFromaccountnr(Fromaccountnr);
        request.setToaccountnr(accountFromTo);
        request.setAmount(amount);

        Account accFrom = new Account(fromaccountnr, BigDecimal.TEN);
        Account accTo = new Account(Fromaccountnr, BigDecimal.TEN);

        when(balRepo.getAccountForUpdate(accountFromId)).thenReturn(Optional.of(accFrom));
        when(balRepo.getAccountForUpdate(accountFromTo)).thenReturn(Optional.of(accTo));

        balService.transferBalances(request);
    }
}
