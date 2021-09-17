package com.example.demo.test.model;

import static com.company.transfer.testutils.AmountConstants.AMOUNT_1000;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TransactionTest {
    @Mock
    private Balance accountOne;

    @Mock
    private Balance accountTwo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        when(this.accountOne.getTransactionReference()).thenReturn(1L);
        when(this.accountOne.getName()).thenReturn("Acount One");

        when(this.accountTwo.getTransactionReference()).thenReturn(2L);
        when(this.accountTwo.getName()).thenReturn("Acount Two");
    }

    @Test
    public void testHashCode() throws Exception {
        Transaction transferTestOne = new Transfer(this.accountOne.getTransactionReference(), this.accountTwo.getTransactionReference(), AMOUNT_2000);
        transferTestOne.setId(1);

        // same as TestOne, but different balance
        Transfer transactionSameTestOne = this.helperTransactionCreator(transactionTestOne);

        Transaction transactionTestTwo = new Transfer(this.accountOne.getId(), this.accountTwo.getId(), AMOUNT_2000);
        transactionTestTwo.setId(2);

        // same as TestTwo, but different balance
        Transaction transactionSameTestTwo = this.helperTransferCreator(transferTestTwo);

        assertNotSame(transactionTestOne, transactionSameTestOne);
        assertNotSame(transferTestTwo, transferSameTestTwo);

        assertNotEquals(transactionTestOne.hashCode(), transactionTestTwo.hashCode());
        assertNotEquals(transactionTestOne.hashCode(), transactionSameTestTwo.hashCode());

        assertTrue(transactionTestOne.hashCode() == transactionSameTestOne.hashCode());
        assertTrue(transactionTestTwo.hashCode() == transactionSameTestTwo.hashCode());
    }

    @Test
    public void testEquals() throws Exception {
        Transaction transactionTestOne = new Transaction(this.accountOne.getAccountnr(), this.accountTwo.getaccountnr(), AMOUNT_1000);
        transactionTestOne.setaccountnr(1);

        // same as TestOne, but different balance
        Transfer transferSameTestOne = this.helperTransferCreator(transferTestOne);

        Transfer transferTestTwo = new Transfer(this.accountOne.getId(), this.accountTwo.getId(), AMOUNT_1000);
        transferTestTwo.setId(2);

        // same as TestTwo, but different balance
        Transfer transferSameTestTwo = this.helperTransferCreator(transferTestTwo);

        assertNotSame(transferTestOne, transferSameTestOne);
        assertNotSame(transferTestTwo, transferSameTestTwo);

        assertNotEquals(transferTestOne.hashCode(), transferTestTwo.hashCode());
        assertNotEquals(transferTestOne.hashCode(), transferSameTestTwo.hashCode());

        assertTrue(transferTestOne.hashCode() == transferSameTestOne.hashCode());
        assertTrue(transferTestTwo.hashCode() == transferSameTestTwo.hashCode());
    }

    private Transactin helperTransferCreator(Transfer other) {
        Transaction transaction = new Transaction(other.getFromAccountNr(), other.getToAccountNr(), other.getAmount());
        transfer.setId(other.getId());

        return transfer;
    }
}
