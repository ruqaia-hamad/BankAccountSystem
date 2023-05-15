package com.BankAccountSystem.BankAccountSystem.DTO;

import java.util.Date;

public class MonthlyTransactionForAccountDTO {

    private Integer transactionId;
    private Date transactionDate;

    private Double transactionAmount;

    public Integer getTransactionId() {
        return transactionId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public MonthlyTransactionForAccountDTO(Integer transactionId, Date transactionDate, Double transactionAmount) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.transactionAmount = transactionAmount;
    }
}
