package com.BankAccountSystem.BankAccountSystem.RequsetObject;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class AccountRequest {

    private Double amount ;

    private Integer accountNumber;
    private  String createdDate;
    private Boolean isActive;
   private Integer customerId;
}
