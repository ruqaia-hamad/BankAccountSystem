package com.BankAccountSystem.BankAccountSystem.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Entity
@Setter
@Getter
@Data
@Table(name = "account_transaction")
public class Transaction  extends  BaseEntity{


    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    Account account;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double amount;
    private Date transactionDate;

}
