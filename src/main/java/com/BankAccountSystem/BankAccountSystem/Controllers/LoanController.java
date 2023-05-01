package com.BankAccountSystem.BankAccountSystem.Controllers;


import com.BankAccountSystem.BankAccountSystem.RequsetObject.AccountRequest;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.LoanRequest;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.LoanRequestForUpdate;
import com.BankAccountSystem.BankAccountSystem.Services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/Loan")
public class LoanController {


    @Autowired
    LoanService loanService;


    @RequestMapping(value = "/createLoan", method = RequestMethod.POST)
    public String createLoan(@RequestBody LoanRequest loanRequest)throws ParseException {
        try {
            loanService.CreateLoan(loanRequest);
            return " Loan created Successfully ";
        } catch (Exception e) {

            return "Loan creation failed";
        }

    }


    @RequestMapping(value = "/updateLoan", method = RequestMethod.POST)
    public String updateLoan(@RequestBody LoanRequestForUpdate loanRequestForUpdate)throws ParseException {
        try {
            loanService.updateLoan(loanRequestForUpdate);
            return " Loan updated Successfully ";
        } catch (Exception e) {

            return "Loan update failed";
        }

    }
}
