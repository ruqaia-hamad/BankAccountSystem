package com.BankAccountSystem.BankAccountSystem.Services;

import com.BankAccountSystem.BankAccountSystem.Models.Account;
import com.BankAccountSystem.BankAccountSystem.Models.Customer;
import com.BankAccountSystem.BankAccountSystem.Models.Loan;
import com.BankAccountSystem.BankAccountSystem.Repositories.CustomerRepository;
import com.BankAccountSystem.BankAccountSystem.Repositories.LoanRepository;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.AccountRequest;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.LoanRequest;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.LoanRequestForUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class LoanService {


    @Autowired
    LoanRepository loanRepository;

    @Autowired
    CustomerRepository customerRepository;


    public void CreateLoan(LoanRequest loanRequest) throws ParseException {
        Loan loan=new Loan();
        loan.setAmount(loanRequest.getAmount());
        loan.setInsertRate(loanRequest.getInsertRate());
        loan.setCreatedDate(new Date());
        loan.setIsActive(loanRequest.getIsActive());
        Customer customer = customerRepository.getCustomerById(loanRequest.getCustomerId());
        loan.setCustomer(customer);
        loanRepository.save(loan);
    }

    public void updateLoan(LoanRequestForUpdate loanRequestForUpdate) throws ParseException {
        Loan loan=new Loan();
        loan.setId(loanRequestForUpdate.getId());
        loan.setAmount(loanRequestForUpdate.getAmount());
        loan.setInsertRate(loanRequestForUpdate.getInsertRate());
        loan.setCreatedDate(new Date());
        loan.setIsActive(loanRequestForUpdate.getIsActive());
        Customer customer = customerRepository.getCustomerById(loanRequestForUpdate.getCustomerId());
        loan.setCustomer(customer);
        loanRepository.save(loan);
    }

    public void deleteLoan(Integer id) {
        loanRepository.deleteLoan(id);
    }

    public Loan calculateLoanInterest(Integer loanId, Double interestRate) {

        Loan loan = loanRepository.getLoanById(loanId);
        Double currentBalance = loan.getAmount();
        Double interestCalculation= currentBalance * interestRate;
        Double newBalance=currentBalance+interestCalculation;
        loan.setAmount(newBalance);
        loanRepository.save(loan);
        return loan;
    }
}
