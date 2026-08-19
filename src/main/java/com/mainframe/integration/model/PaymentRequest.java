package com.mainframe.integration.model;

import java.math.BigDecimal;

public class PaymentRequest {

    private String customerId;
    private String accountNumber;
    private BigDecimal amount;
    private String currency;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId){
        this.customerId = customerId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency(){
        return currency;
    }

    public void setCurrency(String currency){
        this.currency = currency;
    }
}
