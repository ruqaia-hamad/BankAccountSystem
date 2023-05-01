package com.BankAccountSystem.BankAccountSystem.RequsetObject;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class CustomerRequestForCreateCustomer {
    private String  customerName;

    private String email;

    private String phoneNumber;
    private  String createdDate;
   private Boolean isActive;

}
