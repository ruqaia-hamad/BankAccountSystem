package com.BankAccountSystem.BankAccountSystem.DTO;

import java.time.LocalDate;

public class LoanPaymentDTO {



    private Integer id;

    private Integer loanId;

    private Double amount;

    private Double paymentAmount;

    private LocalDate paymentDate;

    public Integer getId() {
        return id;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public Double getAmount() {
        return amount;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }


    public LoanPaymentDTO(Integer id, Integer loanId, Double amount, Double paymentAmount, LocalDate paymentDate) {
        this.id = id;
        this.loanId = loanId;
        this.amount = amount;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
    }
}
