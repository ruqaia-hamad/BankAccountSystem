package com.BankAccountSystem.BankAccountSystem.Services;

public class InvalidLoanStatusException extends RuntimeException{
    public InvalidLoanStatusException(String message) {
        super(message);
    }
}
