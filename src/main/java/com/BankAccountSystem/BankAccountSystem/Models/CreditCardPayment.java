package com.BankAccountSystem.BankAccountSystem.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@Data
public class CreditCardPayment {

    @ManyToOne
    @JoinColumn(name = "creditCard_id", referencedColumnName = "id")
    CreditCard creditCard;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double paymentAmount;

    private LocalDate paymentDate;
}
