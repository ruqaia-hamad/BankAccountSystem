package com.BankAccountSystem.BankAccountSystem.RequsetObject;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class CreditCardRequest {

    private Integer cardNumber;

    private Double creditLimit;
    private  String createdDate;
    private Boolean isActive;
    private Integer customerId;

}
