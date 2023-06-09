package com.BankAccountSystem.BankAccountSystem.Controllers;


import com.BankAccountSystem.BankAccountSystem.Models.Loan;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.LoanRequest;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.LoanRequestForUpdate;
import com.BankAccountSystem.BankAccountSystem.SchedulesJobs.Schedule;
import com.BankAccountSystem.BankAccountSystem.Services.LoanService;
import com.BankAccountSystem.BankAccountSystem.Services.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/Loan")
public class LoanController {


    @Autowired
    LoanService loanService;


    @Autowired
    Schedule schedule;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/createLoan", method = RequestMethod.POST)
    public String createLoan(@RequestBody LoanRequest loanRequest) throws ParseException {
        try {
            loanService.CreateLoan(loanRequest);
            return " Loan created Successfully ";
        } catch (Exception e) {

            return "Loan creation failed";
        }

    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/updateLoan", method = RequestMethod.POST)
    public String updateLoan(@RequestBody LoanRequestForUpdate loanRequestForUpdate) throws ParseException {
        try {
            loanService.updateLoan(loanRequestForUpdate);
            return " Loan updated Successfully ";
        } catch (Exception e) {

            return "Loan update failed";
        }

    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/deleteLoan", method = RequestMethod.GET)
    public String deleteLoan(Integer id) {
        try {
            loanService.deleteLoan(id);
            return " Loan deleted Successfully ";
        } catch (Exception e) {

            return "Loan delete failed";
        }

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/calculateLoanInterest")
    public ResponseEntity<String> calculateCardInterest(@RequestParam Integer loanId, Double interestRate) {
        try {

            Loan loan = loanService.calculateLoanInterest(loanId, interestRate);

            if (loan == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Loan not found with id: " + loanId);
            }
            String message = "Interest calculation made successfully\n" +
                    "loan Id: " + loan.getId() + "\n" +
                    "Interest Rate: " + interestRate + "\n" +
                    "Loan new Balance: " + loan.getAmount();
            return ResponseEntity.ok(message);
        } catch (Exception e) {

            String errorMessage = e.getMessage();
            return ResponseEntity.badRequest().body(errorMessage);
        }
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/makePaymentFromLoan")
    public ResponseEntity<String> makePaymentFromLoan(@RequestParam Integer loanId, Double amount) {
        try {
            Loan loan = loanService.makePaymentFromLoan(loanId, amount);

            if (loan == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Loan not found with id: " + loanId);
            }
            String message = "Payment made successfully\n" +
                    "Loan id: " + loan.getId() + "\n" +
                    "Payment Amount: " + amount + "\n" +
                    "Loan new balance: " + loan.getAmount();
            return ResponseEntity.ok(message);
        } catch (Exception e) {

            String errorMessage = e.getMessage();
            return ResponseEntity.badRequest().body(errorMessage);
        }
    }

    @PreAuthorize("hasRole('USER')")

    @PostMapping("/loanStatus")
    public String approveOrRejectLoan(@RequestParam Integer loanId, double creditScore) {
        try {
            Loan loan = loanService.approveOrRejectLoan(loanId, creditScore);
            String message = loan.getStatus().equals("approved") ? "Loan application approved" : "Loan application rejected";
            return message;
        } catch (ResourceNotFoundException e) {
            return "Loan not found";
        } catch (Exception e) {
            return "An error occurred while processing the request";
        }
    }


}
