package com.BankAccountSystem.BankAccountSystem.SchedulesJobs;


import com.BankAccountSystem.BankAccountSystem.Models.Account;
import com.BankAccountSystem.BankAccountSystem.Models.Customer;
import com.BankAccountSystem.BankAccountSystem.Models.Loan;
import com.BankAccountSystem.BankAccountSystem.Repositories.AccountRepository;
import com.BankAccountSystem.BankAccountSystem.Repositories.CustomerRepository;
import com.BankAccountSystem.BankAccountSystem.Repositories.LoanRepository;
import com.BankAccountSystem.BankAccountSystem.Services.AccountService;
import com.BankAccountSystem.BankAccountSystem.Services.LoanService;
import com.BankAccountSystem.BankAccountSystem.Slack.SlackClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Schedule {
    @Autowired
    SlackClient slackClient;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private AccountService  accountService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void calculateDailyLoanInterest() {
        List<Loan> loans = loanRepository.getActiveLoans();
        for (Loan loan : loans) {
            Double interestRate = loan.getInsertRate() / 365;
            Double currentBalance = loan.getAmount();
            Double interestCalculation= currentBalance * interestRate;
            Double newBalance=currentBalance+interestCalculation;
            loan.setAmount(newBalance);
            loanRepository.save(loan);
            String message = "Loan ID: " + loan.getId() + "\n" +
                    "Previous Balance: " + currentBalance + "\n" +
                    "Daily Interest Rate: " + interestRate + "\n" +
                    "Interest Calculation: " + interestCalculation + "\n" +
                    "New Balance: " + newBalance;

            slackClient.sendMessage(message);
        }
    }

}

