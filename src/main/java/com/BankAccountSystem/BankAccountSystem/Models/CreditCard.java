package com.BankAccountSystem.BankAccountSystem.Models;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Data
public class CreditCard extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer cardNumber;

    private Double creditLimit;
    private String status;
    @ManyToOne
    @JoinColumn(name = "Customer_Id", referencedColumnName = "id")
    Customer customer;

}
