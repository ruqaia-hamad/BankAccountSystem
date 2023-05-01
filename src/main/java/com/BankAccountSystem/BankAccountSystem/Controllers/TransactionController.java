package com.BankAccountSystem.BankAccountSystem.Controllers;


import com.BankAccountSystem.BankAccountSystem.RequsetObject.LoanRequest;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.TransactionRequest;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.TransactionRequestForUpdate;
import com.BankAccountSystem.BankAccountSystem.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping(value = "/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;



    @RequestMapping(value = "/createTransaction", method = RequestMethod.POST)
    public String createTransaction(@RequestBody TransactionRequest transactionRequest)throws ParseException {
        try {
            transactionService.CreateNewTransaction(transactionRequest);
            return " Transaction created Successfully";
        } catch (Exception e) {

            return "Transaction creation failed";
        }

    }


    @RequestMapping(value = "/updateTransaction", method = RequestMethod.POST)
    public String updateTransaction(@RequestBody TransactionRequestForUpdate transactionRequestForUpdate)throws ParseException {
        try {
            transactionService.updateTransaction(transactionRequestForUpdate);
            return " Transaction updated Successfully ";
        } catch (Exception e) {

            return "Transaction updated failed";
        }

    }


    @RequestMapping(value = "/updateTransaction", method = RequestMethod.POST)
    public String deleteTransaction(Integer id) {
        try {
            transactionService.deleteTransaction(id);
            return " Transaction deleted Successfully ";
        } catch (Exception e) {

            return "Transaction delete failed";
        }

    }

}
