package com.BankAccountSystem.BankAccountSystem.Controllers;


import com.BankAccountSystem.BankAccountSystem.RequsetObject.AccountRequest;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.CustomerRequestForCreateCustomer;
import com.BankAccountSystem.BankAccountSystem.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

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
}
