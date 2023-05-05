package com.BankAccountSystem.BankAccountSystem.RequsetObject;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoanRequest {


    private Double amount;
    private Double insertRate;
    private  String createdDate;
    private Boolean isActive;
    private Integer customerId;
    private String status;


}
