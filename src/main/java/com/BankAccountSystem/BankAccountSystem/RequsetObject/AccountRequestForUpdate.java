package com.BankAccountSystem.BankAccountSystem.RequsetObject;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class AccountRequestForUpdate {
    private Integer id;
    private Double amount ;
    private Integer accountNumber;
    private Boolean isActive;
    private Integer customerId;
}
