package com.BankAccountSystem.BankAccountSystem.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Setter
@Getter
@Data
public class LoanPayment {

    @ManyToOne
    @JoinColumn(name = "loan_Id", referencedColumnName = "id")
    Loan loan;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double paymentAmount;

    private LocalDate paymentDate;
}
