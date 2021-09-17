package com.example.demo.Entity;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import com.example.demo.exceptions.EntityCreationException;
import org.springframework.dao.DataIntegrityViolationException;



@Entity
@Table(name = "transactions")
public class Transaction {

    private static final DateTimeFormatter TRANSFER_TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
    @Id
    @Column(nullable = false, unique = true)
    private Long TransactionReference;

    @Version
    private long version;


    @Column(nullable = false)
    private Long accountnr;

    @Column(nullable = false)
    private BigDecimal amount;

    private long fromaccountnr;

    private long toaccountnr;

    @Column(nullable = false)
    private String TransactionDateTime;

    /**
     * Instantiates a new user transaction.
     *
     * @param transferAmount the amount
     * @param fromAccountnr  the from account Nr
     * @param toAccountnr    the to account Nr
     */

    public Transaction(Long fromAccountnr, Long toAccountnr, BigDecimal transferAmount) {
        this.validateBeforeCreation(fromAccountnr, toAccountnr, amount);

        this.fromaccountnr = fromaccountnr;

        this.toaccountnr = toaccountnr;

        this.amount = transferAmount;
        this.TransactionDateTime = LocalDateTime.now().format(TRANSFER_TIMESTAMP_FORMATTER);

    }

    public Long getaccountnr() {
        return this.accountnr;
    }

    public void setaccountnr(long accountnr) {
        this.accountnr = accountnr;
    }

    public Long getTransactionReference() {
        return this.TransactionReference;
    }

    public void setTransactionReference(long transactionReference) {
        this.TransactionReference = TransactionReference;
    }

    public String getTimestamp() {
        return this.TransactionDateTime;
    }

    public void setTimestamp(String timestamp) {
        this.TransactionDateTime = timestamp;
    }

    public Long getVersion() {
        return this.version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public Long getFromaccountnr() {
        return this.fromaccountnr;
    }

    public void setFromaccountnr(long fromaccountnr) {
        this.fromaccountnr = fromaccountnr;
    }

    public Long getToaccountnr() {
        return this.toaccountnr;
    }

    public void setToaccountnr(long toaccountnr) {
        this.toaccountnr = toaccountnr;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((TransactionReference == null) ? 0 : TransactionReference.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Transaction other = (Transaction) obj;
        if (TransactionReference == null) {
            if (other.TransactionReference != null) {
                return false;
            }
        } else if (!TransactionReference.equals(other.TransactionReference)) {
            return false;
        }
        return true;
    }

    /**
     * Help method to validate business data before entity creation
     */
    private void validateBeforeCreation(long fromaccountnr, long toaccountnr, BigDecimal transferamount) throws EntityCreationException {
        try {
            if (fromaccountnr == toaccountnr) {
                throw new EntityCreationException("account number from and Destination account cannot be the same");
            }

            transferAmount = Optional.of(transferAmount).get();
            if (transferAmount.compareTo(BigDecimal.ZERO) <= 0) {
                throw new EntityCreationException("Transfer amount must not be less than zero");
            }
        } catch (NullPointerException e) {
            throw new EntityCreationException(e);
        }

    }

    private void Duplicatetransactionauthenticator(Long TransactionReference, String TransactionDateTime) {
        try {
        } catch (DataIntegrityViolationException e) {
            if (TransactionReference.TransactionDateTime == other.TransactionReference.TransactionDateTime) {
            }
            throw DuplicateTransactionException(e);
            {
                System.out.println("Duplicate Transaction");
            }

        }
    }
}
