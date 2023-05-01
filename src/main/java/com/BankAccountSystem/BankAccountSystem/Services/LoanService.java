package com.BankAccountSystem.BankAccountSystem.Services;

import com.BankAccountSystem.BankAccountSystem.Models.Account;
import com.BankAccountSystem.BankAccountSystem.Models.Customer;
import com.BankAccountSystem.BankAccountSystem.Models.Loan;
import com.BankAccountSystem.BankAccountSystem.Repositories.CustomerRepository;
import com.BankAccountSystem.BankAccountSystem.Repositories.LoanRepository;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.AccountRequest;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.LoanRequest;
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
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date convetedDate = formatter.parse(loanRequest.getCreatedDate());
        loan.setCreatedDate(convetedDate);
        loan.setIsActive(loanRequest.getIsActive());
        Customer customer = customerRepository.getCustomerById(loanRequest.getCustomerId());
        loan.setCustomer(customer);
        loanRepository.save(loan);
    }
}
