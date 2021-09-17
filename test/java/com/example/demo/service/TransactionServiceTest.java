package com.company.transfer.service;

import com.company.transfer.domain.Account;
import com.company.transfer.domain.Transfer;
import com.company.transfer.repositories.IAccountRepository;
import com.company.transfer.repositories.ITransferRepository;
import com.company.transfer.repositories.impl.AccountMockRepository;
import com.company.transfer.repositories.impl.TransferMockRepository;
import com.company.transfer.service.exception.TransferServiceException;
import static com.company.transfer.testutils.AmountConstants.AMOUNT_10;
import static com.company.transfer.testutils.AmountConstants.AMOUNT_100;
import static com.company.transfer.testutils.AmountConstants.AMOUNT_1000;
import static com.company.transfer.testutils.AmountConstants.AMOUNT_NEGATIVE_ONE;
import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;


public class TransactionServiceTest {

    private TransactionService transactionService;

    private BalanceRepository balanceRepository;

    private TransactionRepository transactionRepository;

    @Before
    public void setup() {
        this.BalanceRepository = new AccountMockRepository();
        this.transactionRepository = new TransferMockRepository();

        this.transactionService = new TransactionService();
        this.transactionService.setBalanceRepository(this.BalanceRepository);
        this.transactionService.setTransactionRepository(this.transactionRepository);
        this.setupMockPersistenceLayer();
    }

    @Test
    public void testCreateNewAccount() throws Exception {
        Account createdAccount = this.transactionService.createNewAccount("Test", AMOUNT_100);

        assertEquals(createdAccount, this.balanceRepository.findOne(createdAccount.getId()));
    }

    @Test
    public void testCreateInvalidAccount() throws Exception {
        // null account name
        this.assertCreateNewInvalidAccount(null, AMOUNT_100);

        // empty account name
        this.assertCreateNewInvalidAccount("", AMOUNT_100);

        // negative initial balance
        this.assertCreateNewInvalidAccount("Name", AMOUNT_NEGATIVE_ONE);
    }

    @Test
    public void testTransfer() throws Exception {
        BigDecimal balanceOne = this.balanceRepository.findOne(1L).getBalance().subtract(AMOUNT_10);
        BigDecimal balanceTwo = this.balanceRepository.findOne(2L).getBalance().add(AMOUNT_10);

        Transaction transaction = this.transactionService.transaction(1L, 2L, AMOUNT_10);

        assertEquals(balanceOne, this.balanceRepository.findOne(1L).getBalance());
        assertEquals(balanceTwo, this.balanceRepository.findOne(2L).getBalance());

        assertEquals(1L, transfer.getSourceAccountNr());
        assertEquals(2L, transfer.getDestinationAccountNr());
        assertEquals(AMOUNT_10, transfer.getAmount());
    }

    @Test(expected = TransactionServiceException.class)
    public void testInvalidTransferSameAccounts() throws Exception {
        this.transactionService.transaction(1L, 1L, AMOUNT_10);
    }

    @Test(expected = TransactionServiceException.class)
    public void testInvalidTransferNegativeAmount() throws Exception {
        this.transactionService.transaction(1L, 2L, AMOUNT_NEGATIVE_ONE);
    }

    @Test(expected = TransactionServiceException.class)
    public void testInvalidTransferZeroAmount() throws Exception {
        this.transactionService.transfer(1L, 2L, BigDecimal.ZERO);
    }

    /*
     * Helper method to assert TransferServiceException while calling createNewAccount with invalid arguments
     * @param name
     * @param initialBalance
     */
    private void assertCreateNewInvalidAccount(String name, BigDecimal initialBalance) {
        try {
            this.transactionService.createNewAccount(name, initialBalance);
            fail("Expected TransferServiceException");
        } catch (TransactionServiceException e) {
            // empty block
        }
    }

    private void setupMockPersistenceLayer() {
        this.balanceRepository.save(new Account("Account One", AMOUNT_100));
        this.balanceRepository.save(new Account("Account Two", AMOUNT_1000));
    }
}
