package com.BankAccountSystem.BankAccountSystem.DTO;

import java.util.Date;

public class AccountTransactionDTO {
    private Integer id;
    private Double amount;
    private Date transactionDate;

    private Integer accountId;

    public Double getAmount() {
        return amount;
    }




    public Integer getAccountId() {
        return accountId;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }




    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public AccountTransactionDTO( Integer accountId,Integer id, Double amount, Date transactionDate) {
        this.id = id;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.accountId = accountId;
    }
}
