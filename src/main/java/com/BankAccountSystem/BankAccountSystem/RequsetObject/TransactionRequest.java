package com.BankAccountSystem.BankAccountSystem.RequsetObject;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter
@Data
public class TransactionRequest {

    private Double amount;
    private String transactionDate;
    private Boolean isActive;
    private Integer accountId;
}
