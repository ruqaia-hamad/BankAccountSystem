package com.BankAccountSystem.BankAccountSystem.Models;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Data
public class Account extends  BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double amount ;

    private Integer accountNumber;

    @ManyToOne
    @JoinColumn(name = "Customer_Id", referencedColumnName = "id")
    Customer customer;


    public void updateBalance(Double amount) {
        this.amount += amount;
    }

}
