package com.BankAccountSystem.BankAccountSystem.SchedulesJobs;


import com.BankAccountSystem.BankAccountSystem.Models.Account;
import com.BankAccountSystem.BankAccountSystem.Models.Customer;
import com.BankAccountSystem.BankAccountSystem.Models.Loan;
import com.BankAccountSystem.BankAccountSystem.Repositories.AccountRepository;
import com.BankAccountSystem.BankAccountSystem.Repositories.CustomerRepository;
import com.BankAccountSystem.BankAccountSystem.Repositories.LoanRepository;
import com.BankAccountSystem.BankAccountSystem.Services.AccountService;
import com.BankAccountSystem.BankAccountSystem.Services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Schedule {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private AccountService  accountService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void calculateDailyInterest() {
        List<Customer> customers = customerRepository.getAllCustomers();
        for (Customer customer : customers) {
            List<Loan> loans = loanRepository.getLoansByCustomerId(customer.getId());
            for (Loan loan : loans) {
                double interest = loan.getInsertRate() / 365 * loan.getAmount();
                loan.setAmount(loan.getAmount() + interest);
                loanRepository.save(loan);
            }

            }
        }
    }

