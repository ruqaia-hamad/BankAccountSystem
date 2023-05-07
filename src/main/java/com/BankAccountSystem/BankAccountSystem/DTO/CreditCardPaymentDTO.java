package com.BankAccountSystem.BankAccountSystem.DTO;

import java.time.LocalDate;

public class CreditCardPaymentDTO {

    private Integer id;
    private Integer creditCardId;
    private Integer cardNumber;
    private  Double creditLimit;
    private Double paymentAmount;
    private LocalDate paymentDate;

    public Integer getId() {
        return id;
    }

    public Integer getCreditCardId() {
        return creditCardId;
    }

    public Integer getCardNumber() {
        return cardNumber;
    }

    public Double getCreditLimit() {
        return creditLimit;
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

    public void setCreditCardId(Integer creditCardId) {
        this.creditCardId = creditCardId;
    }

    public void setCardNumber(Integer cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCreditLimit(Double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public CreditCardPaymentDTO(Integer id, Integer creditCardId, Integer cardNumber, Double creditLimit, Double paymentAmount, LocalDate paymentDate) {
        this.id = id;
        this.creditCardId = creditCardId;
        this.cardNumber = cardNumber;
        this.creditLimit = creditLimit;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
    }
}
