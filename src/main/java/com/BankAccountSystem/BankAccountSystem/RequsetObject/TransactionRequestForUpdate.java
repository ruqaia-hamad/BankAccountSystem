package com.BankAccountSystem.BankAccountSystem.RequsetObject;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class TransactionRequestForUpdate {
    private Integer id;
    private Double amount;
    private String transactionDate;
    private  String createdDate;
    private Boolean isActive;
    private Integer accountId;
}
