package com.BankAccountSystem.BankAccountSystem.Controllers;


import com.BankAccountSystem.BankAccountSystem.Models.Transaction;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.AccountRequest;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.AccountRequestForUpdate;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.CustomerRequestForCreateCustomer;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.TransactionRequest;
import com.BankAccountSystem.BankAccountSystem.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "Account")
public class AccountController {


    @Autowired
    AccountService accountService;




    @RequestMapping(value = "/createAccount", method = RequestMethod.POST)
    public String createAccount(@RequestBody AccountRequest accountRequest)throws ParseException {
        try {
            accountService.CreateNewAccount(accountRequest);
            return " Account created Successfully ";
        } catch (Exception e) {

            return "Account creation failed";
        }

    }


    @RequestMapping(value = "/updateAccount", method = RequestMethod.POST)
    public String updateAccount(@RequestBody AccountRequestForUpdate accountRequestForUpdate)throws ParseException {
        try {
            accountService.updateAccount(accountRequestForUpdate);
            return " Account updated Successfully ";
        } catch (Exception e) {

            return "Account update failed";
        }

    }


    @RequestMapping(value = "/deleteAccount", method = RequestMethod.GET)
    public String deleteAccount(Integer id){
        try {
            accountService.deleteAccount(id);
            return " Account deleted Successfully ";
        } catch (Exception e) {

            return "Account deletion failed";
        }
    }


    @PostMapping("/updateBalance")
    public String makeTransaction(@RequestBody TransactionRequest transactionRequest) {
        accountService.updateBalance(transactionRequest);
        return "Transaction completed successfully";
    }


    @GetMapping("/{accountId}/interest")
    public ResponseEntity<?> calculateInterestForAccount(@PathVariable Integer accountId) {
        Double interest = accountService.calculateInterestForAccount(accountId);
        String message = "Interest calculated successfully.";
        Map<String, Object> response = new HashMap<>();
        response.put("interest", interest);
        response.put("message", message);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{accountId}/accountStatement")
    public ResponseEntity<String> generateMonthlyStatementForAccount(@PathVariable Integer accountId) {
        String statement = accountService.generateMonthlyStatement(accountId);
        return ResponseEntity.ok(statement);
    }



    @GetMapping("/{accountId}/accountHistory")
    public ResponseEntity<List<Transaction>> getAccountHistory(@PathVariable Integer accountId) {
        List<Transaction> transactions = accountService.getAccountHistory(accountId);
        return ResponseEntity.ok(transactions);
    }


}

