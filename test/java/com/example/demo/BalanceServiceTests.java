//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.utils;

import com.example.demo.exceptions.AccountNotExistException;
import com.example.demo.exceptions.OverDraftException;
import com.example.demo.exceptions.SystemException;
import com.example.demo.Entity.Balances;
import com.example.demo.Entity.Transaction;
import com.example.demo.repositories.TransactionRepository;
import com.example.demo.service.TransactionService;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BalanceServiceTest {
    @Mock
    BalancesRepository accRepo;
    @InjectMocks
    BalanceService accService;

    public BalanceServiceTests() {
    }

    @Test
    public void testRetrieveBalance() {
        Mockito.when(this.accRepo.findByaccountnr(1L)).thenReturn(Optional.of(new Account(1L, BigDecimal.ONE)));
        Assert.assertEquals(BigDecimal.ONE, this.accService.retrieveBalances(1L).getBalance());
    }

    @Test(
        expected = AccountNotExistException.class
    )
    public void testRetrieveBalanceFromInvalidAccount() {
        Mockito.when(this.accRepo.findByAccountId(1L)).thenReturn(Optional.empty());
        this.accService.retrieveBalances(1L);
    }

    @Test
    public void testTransferBalance() throws Exception, Exception, Exception {
        Long FromAccountNr = 1L;
        Long accountFromTo = 2L;
        BigDecimal amount = new BigDecimal(10);
        Transaction request = new Transaction();
        request.setFromAccountNr(Fromaccountnr);
        request.setToAccountNr(accountFromTo);
        request.setAmount(amount);
        Account accFrom = new Account(Fromaccountnr, BigDecimal.TEN);
        Account accTo = new Account(Fromaccountnr, BigDecimal.TEN);
        Mockito.when(this.accRepo.getAccountForUpdate(Fromaccountnr)).thenReturn(Optional.of(accFrom));
        Mockito.when(this.accRepo.getAccountForUpdate(accountFromTo)).thenReturn(Optional.of(accTo));
        this.accService.transferBalances(request);
        Assert.assertEquals(BigDecimal.ZERO, accFrom.getBalance());
        Assert.assertEquals(BigDecimal.TEN.add(BigDecimal.TEN), accTo.getBalance());
    }

    @Test(
        expected = OverDraftException.class
    )
    public void testOverdraftBalance() throws OverDraftException, AccountNotExistException, SystemException {
        Long FromAccountNr = 1L;
        Long accountFromTo = 2L;
        BigDecimal amount = new BigDecimal(20);
Transaction request = new Transaction();
        request.setFromAccountNr(Fromaccountnr);
        request.setToAccountNr(accountFromTo);
        request.setAmount(amount);
        Balances accFrom = new Account(FromAccountNr, BigDecimal.TEN);
        Balances accTo = new Account(FromAccountNr, BigDecimal.TEN);
        Mockito.when(this.accRepo.getAccountForUpdate(accountFromId)).thenReturn(Optional.of(accFrom));
        Mockito.when(this.accRepo.getAccountForUpdate(accountFromTo)).thenReturn(Optional.of(accTo));
        this.accService.transferBalances(request);
    }
}
