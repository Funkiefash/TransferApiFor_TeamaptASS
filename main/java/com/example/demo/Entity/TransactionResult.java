package com.example.demo.Entity;

import java.math.BigDecimal;

public class TransactionResult {

    private String fromaccountnr;

    private BigDecimal balanceAfterTransfer;

    public String getfromaccountnr() {
        return fromaccountnr;
    }

    public void setfromaccountnr(String Fromaccountnr) {
        this.fromaccountnr = fromaccountnr;
    }

    public BigDecimal getBalanceAfterTransfer() {
        return balanceAfterTransfer;
    }

    public void setBalanceAfterTransfer(BigDecimal balanceAfterTransfer) {
        this.balanceAfterTransfer = balanceAfterTransfer;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fromaccountnr == null) ? 0 : fromaccountnr.hashCode());
        result = prime * result + ((balanceAfterTransfer == null) ? 0 : balanceAfterTransfer.hashCode());
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
        TransactionResult other = (TransactionResult) obj;
        if (fromaccountnr == null) {
            if (other.fromaccountnr != null)
                return false;
        } else if (!fromaccountnr.equals(other.fromaccountnr))
            return false;
        if (balanceAfterTransfer == null) {
            if (other.balanceAfterTransfer != null)
                return false;
        } else if (!balanceAfterTransfer.equals(other.balanceAfterTransfer))
            return false;
        return true;
    }

}
