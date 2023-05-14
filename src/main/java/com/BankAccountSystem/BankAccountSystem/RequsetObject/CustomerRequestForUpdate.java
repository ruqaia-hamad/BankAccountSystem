package com.BankAccountSystem.BankAccountSystem.RequsetObject;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class CustomerRequestForUpdate {
    private Integer id;
    private String  customerName;

    private String email;

    private String phoneNumber;
    private Boolean isActive;
}
