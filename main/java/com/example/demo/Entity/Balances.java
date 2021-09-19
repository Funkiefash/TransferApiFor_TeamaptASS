package com.example.demo.Entity;


import com.example.demo.exceptions.EntityCreationException;

import java.math.BigDecimal;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

   @Entity
   @Table(name = "balances")

public class Balances {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // primary key
    @Column( nullable=false, unique=true)
    private String accountnr;


    @Column( nullable = false)
private String name;


    @Column( nullable = false)
   private BigDecimal balance;
    /**
     * Account default constructor - JPA usage
     */
    protected Balances() {
        super();
    }

    public Balances( String name, BigDecimal initialBalance) throws EntityCreationException {
        // class' default constructor
        this();
        this.validateBeforeCreation(name, initialBalance);
        this.name = name;
        this.balance = initialBalance;
    }
// Getters and setters of
    // the properties

    public Long getaccountnr() {
        return getaccountnr();
    }
        public void setaccountnr(String accountnr) {
            this.accountnr = accountnr;
        }


    public Long getbalance() {
        return getbalance();
    }
    public void setBalance(BigDecimal balance) {this.balance = balance;}

    public Long getname() {
        return getname();
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accountnr == null) ? 0 : accountnr.hashCode());
        result = prime * result + ((balance == null) ? 0 : balance.hashCode());
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
        Balances other = (Balances) obj;
        if (accountnr == null) {
            if (other.accountnr != null)
                return false;
        } else if (!accountnr.equals(other.accountnr))
            return false;
        if (balance == null) {
            if (other.balance != null)
                return false;
        } else if (!balance.equals(other.balance))
            return false;
        return true;
    }

    public boolean hasEnoughBalanceForTransfer(BigDecimal transferAmount) {
        return this.balance.subtract(transferAmount).compareTo(BigDecimal.ZERO) >= 0;
    }

    public boolean subtract(BigDecimal transferAmount) {
        if (this.hasEnoughBalanceForTransfer(transferAmount)) {
            this.balance = this.balance.subtract(transferAmount);
            return true;
        }

        return false;
    }

    public void add(BigDecimal transferAmount) {
        this.balance = this.balance.add(transferAmount);
    }

    /**
     * Helper method to validate business data before entity creation
     * @param name the account name
     * @param initialBalance the account initial balance
     */
    private void validateBeforeCreation(String name, BigDecimal initialBalance) throws EntityCreationException {
        try {
            name = Optional.of(name).get().trim();
            if (name.length() < 1) {
                throw new EntityCreationException("Account name must be provided");
            }

            initialBalance = Optional.of(initialBalance).get();
            if (initialBalance.compareTo(BigDecimal.ZERO) < 0) {
                throw new EntityCreationException("Account initial balance cannot be negative");
            }
        } catch (NullPointerException e) {
            throw new EntityCreationException(e);
        }
    }
}
