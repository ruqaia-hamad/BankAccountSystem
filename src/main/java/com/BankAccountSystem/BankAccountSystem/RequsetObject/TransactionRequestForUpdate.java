package com.BankAccountSystem.BankAccountSystem.RequsetObject;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Data
public class TransactionRequestForUpdate {
    private Integer id;
    private Double amount;
    private Date transactionDate;
    private  String createdDate;
    private Boolean isActive;
    private Integer accountId;
}
