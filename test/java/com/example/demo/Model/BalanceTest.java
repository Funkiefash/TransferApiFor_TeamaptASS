package com.example.demo.test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

import static com.example.demo.test.testutils.AmountConstants.*;

public class BalanceTest {
    /**
     * Test case for hashCode
     */
    @Test
    public void testHashCode() throws Exception {
        Balances accountTestOne = new Balance("TestOne", AMOUNT_1000);
        accountTestOne.setAccountNr(1);

        // same as TestOne, but a different balance
        Balances accountSameTestOne = this.helperAccountCreator(accountTestOne);
        accountSameTestOne.setBalance(AMOUNT_50000);

        Balances accountTestTwo = new Balance("TestTwo", AMOUNT_1000);
        accountTestTwo.setId(2);

        // same as TestTwo, but a different balance
        Balances accountSameTestTwo = this.helperAccountCreator(accountTestTwo);
        accountSameTestTwo.setBalance(AMOUNT_50000);

        assertNotSame(accountTestOne, accountSameTestOne);
        assertNotSame(accountTestTwo, accountSameTestTwo);

        assertNotEquals(accountTestOne.hashCode(), accountTestTwo.hashCode());
        assertNotEquals(accountTestOne.hashCode(), accountSameTestTwo.hashCode());

        assertTrue(accountTestOne.hashCode() == accountSameTestOne.hashCode());
        assertTrue(accountTestTwo.hashCode() == accountSameTestTwo.hashCode());
    }

    /**
     * Test case for equals
     */
    @Test
    public void testEquals() throws Exception {
        Balances accountTestOne = new Balance("TestOne", AMOUNT_1000);
        accountTestOne.setId(1);

        // same as TestOne, but different balance
        Balances accountSameTestOne = this.helperAccountCreator(accountTestOne);
        accountSameTestOne.setBalance(AMOUNT_50000);

        Balances accountTestTwo = new Account("TestTwo", AMOUNT_1000);
        accountTestTwo.setId(2);

        // same as TestTwo, but different balance
        Balances accountSameTestTwo = this.helperAccountCreator(accountTestTwo);
        accountSameTestTwo.setBalance(AMOUNT_1000000);

        assertNotSame(accountTestOne, accountSameTestOne);
        assertNotSame(accountTestTwo, accountSameTestTwo);

        assertNotEquals(accountTestOne, accountTestTwo);
        assertNotEquals(accountTestOne, accountSameTestTwo);

        assertEquals(accountTestOne, accountSameTestOne);
        assertEquals(accountTestTwo, accountSameTestTwo);
    }

    @Test
    public void testHasEnoughBalanceForTransfer() throws Exception {
        // 2000 in balance
        Balances accountBalanceTwoThousand = new Balance("TestOne", AMOUNT_2000);

        assertTrue(accountBalanceTwoThousand.hasEnoughBalanceForTransfer(BigDecimal.ZERO));

        assertTrue(accountBalanceTwoThousand.hasEnoughBalanceForTransfer(BigDecimal.TEN));

        assertTrue(accountBalanceTwoThousand.hasEnoughBalanceForTransfer(AMOUNT_100));

        assertTrue(accountBalanceTwoThousand.hasEnoughBalanceForTransfer(AMOUNT_2000));

        assertFalse(accountBalanceTwoThousand.hasEnoughBalanceForTransfer(AMOUNT_2000.add(BigDecimal.ONE)));

        // 0 in balance
        Balances accountBalanceZero = new Balance("TestTwo", BigDecimal.ZERO);

        assertTrue(accountBalanceZero.hasEnoughBalanceForTransfer(BigDecimal.ZERO));

        assertFalse(accountBalanceZero.hasEnoughBalanceForTransfer(BigDecimal.ONE));

        assertFalse(accountBalanceZero.hasEnoughBalanceForTransfer(BigDecimal.TEN));

        assertFalse(accountBalanceZero.hasEnoughBalanceForTransfer(AMOUNT_100));

        assertFalse(accountBalanceZero.hasEnoughBalanceForTransfer(AMOUNT_2000));

        assertFalse(accountBalanceZero.hasEnoughBalanceForTransfer(AMOUNT_1000.add(BigDecimal.ONE)));

        // 1 in balance
        Balances accountBalanceOne = new Balances("TestTwo", BigDecimal.ONE);

        assertTrue(accountBalanceOne.hasEnoughBalanceForTransfer(BigDecimal.ZERO));

        assertTrue(accountBalanceOne.hasEnoughBalanceForTransfer(BigDecimal.ONE));

        assertFalse(accountBalanceOne.hasEnoughBalanceForTransfer(BigDecimal.TEN));

        assertFalse(accountBalanceOne.hasEnoughBalanceForTransfer(AMOUNT_100));

        assertFalse(accountBalanceOne.hasEnoughBalanceForTransfer(AMOUNT_2000));

        assertFalse(accountBalanceOne.hasEnoughBalanceForTransfer(AMOUNT_2000.add(BigDecimal.ONE)));
    }

    private Balances helperAccountCreator(Balance other) {
        Balances balance = new Balance(other.getName(), other.getbalance());
        Balances.setaccountnr(other.getaccountnr());
        return balance;
    }
}
