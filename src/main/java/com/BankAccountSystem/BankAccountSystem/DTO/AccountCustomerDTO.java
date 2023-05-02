package com.BankAccountSystem.BankAccountSystem.DTO;

import java.util.Date;

public class AccountCustomerDTO {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private Double amount ;

    private Integer accountNumber;
    private Date createdDate;
    private Boolean isActive;
    private Integer customerId;

    public Double getAmount() {
        return amount;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Boolean getActive() {
        return isActive;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public AccountCustomerDTO(Integer id, Double amount, Integer accountNumber, Date createdDate, Boolean isActive, Integer customerId) {
        this.id = id;
        this.amount = amount;
        this.accountNumber = accountNumber;
        this.createdDate = createdDate;
        this.isActive = isActive;
        this.customerId = customerId;
    }
}
