package com.BankAccountSystem.BankAccountSystem.Services;

import com.BankAccountSystem.BankAccountSystem.Models.Account;
import com.BankAccountSystem.BankAccountSystem.Models.Customer;
import com.BankAccountSystem.BankAccountSystem.Models.Loan;
import com.BankAccountSystem.BankAccountSystem.Models.LoanPayment;
import com.BankAccountSystem.BankAccountSystem.Repositories.CustomerRepository;
import com.BankAccountSystem.BankAccountSystem.Repositories.LoanPaymentRepository;
import com.BankAccountSystem.BankAccountSystem.Repositories.LoanRepository;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.AccountRequest;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.LoanRequest;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.LoanRequestForUpdate;
import com.BankAccountSystem.BankAccountSystem.Slack.SlackClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Service
public class LoanService {


    @Autowired
    LoanRepository loanRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    LoanPaymentRepository paymentRepository;

    @Autowired
    SlackClient slackClient;

    public void CreateLoan(LoanRequest loanRequest) throws ParseException {
        Loan loan = new Loan();
        loan.setAmount(loanRequest.getAmount());
        loan.setInsertRate(loanRequest.getInsertRate());
        loan.setCreatedDate(new Date());
        loan.setIsActive(loanRequest.getIsActive());
        loan.setStatus(loan.getStatus());
        Customer customer = customerRepository.getCustomerById(loanRequest.getCustomerId());
        loan.setCustomer(customer);
        loanRepository.save(loan);
    }

    public void updateLoan(LoanRequestForUpdate loanRequestForUpdate) throws ParseException {
        Loan loan = new Loan();
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
        Double interestCalculation = currentBalance * interestRate;
        Double newBalance = currentBalance + interestCalculation;
        loan.setAmount(newBalance);
        loanRepository.save(loan);
        return loan;
    }

    public Loan makePaymentFromLoan(Integer LoanId, Double paymentAmount) {
        Loan loan = loanRepository.getLoanById(LoanId);
        Double newBalance = loan.getAmount() - paymentAmount;
        if (newBalance < 0) {
            throw new ResourceNotFoundException("Payment amount cannot exceed current balance");
        }

        loan.setAmount(newBalance);
        loanRepository.save(loan);
        LoanPayment payment = new LoanPayment();
        payment.setLoan(loan);
        payment.setPaymentAmount(paymentAmount);
        payment.setPaymentDate(LocalDate.now());
        paymentRepository.save(payment);
        return loan;
    }

    public Loan approveOrRejectLoan(Integer loanId, double creditScore) {
        Loan loan = loanRepository.getLoanById(loanId);

        if (creditScore >= 850) {
            loan.setStatus("approved");
            slackClient.sendMessage("New laon application approved - Loan ID: " + loanId);
        } else {
            loan.setStatus("rejected");
            slackClient.sendMessage("New loan application rejected - Loan ID: " + loanId);
        }

        loanRepository.save(loan);
        return loan;
    }




}
