package com.BankAccountSystem.BankAccountSystem.RequsetObject;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class LoanRequestForUpdate {

    private Integer id;

    private Double amount;
    private Double insertRate;
    private Boolean isActive;
    private Integer customerId;
    private String status;
}
