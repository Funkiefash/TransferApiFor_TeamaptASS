package com.example.demo.Entity;

import java.math.BigDecimal;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class TransactionRequest {

    @NotNull
    @ApiModelProperty(required = true)
    private String fromaccountnr;

    @NotNull
    @ApiModelProperty(required = true)
    private String toaccountnr;


    @NotNull
    @ApiModelProperty(required = true)
    @Min(value = 0, message = "Transfer amount can not be less than zero")
    private BigDecimal amount;

    @JsonCreator
    public TransactionRequest(@NotNull @JsonProperty("accountFromId") String fromaccountnr,
                           @NotNull @JsonProperty("accountToId") String toaccountnr,
                           @NotNull @Min(value = 0, message = "Transfer amount can not be less than zero") @JsonProperty("amount") BigDecimal amount) {
        super();
        this.fromaccountnr = fromaccountnr;
        this.toaccountnr = toaccountnr;
        this.amount = amount;
    }


    public TransactionRequest() {
        super();
    }

    public String getfromaccountnr() {
        return fromaccountnr;
    }

    public void setfromaccountnr(String fromaccountnr) {
        this.fromaccountnr = fromaccountnr;
    }

    public String gettoaccountnr() {
        return toaccountnr;
    }

    public void settoaccountnr(String toaccountnr) {
        this.toaccountnr = toaccountnr;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fromaccountnr == null) ? 0 : fromaccountnr.hashCode());
        result = prime * result + ((toaccountnr == null) ? 0 : toaccountnr.hashCode());
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
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
        TransactionRequest other = (TransactionRequest) obj;
        if (fromaccountnr == null) {
            if (other.fromaccountnr != null)
                return false;
        } else if (!fromaccountnr.equals(other.fromaccountnr))
            return false;
        if (toaccountnr== null) {
            if (other.toaccountnr != null)
                return false;
        } else if (!toaccountnr.equals(other.toaccountnr))
            return false;
        if (amount == null) {
            if (other.amount != null)
                return false;
        } else if (!amount.equals(other.amount))
            return false;
        return true;
    }

}
