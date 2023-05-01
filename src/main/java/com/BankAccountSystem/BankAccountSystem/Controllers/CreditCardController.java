package com.BankAccountSystem.BankAccountSystem.Controllers;


import com.BankAccountSystem.BankAccountSystem.Models.CreditCard;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.AccountRequest;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.CreditCardRequest;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.CreditCardRequestForUpdate;
import com.BankAccountSystem.BankAccountSystem.Services.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping(value= "/creditCard")
public class CreditCardController {

    @Autowired
    CreditCardService creditCardService;

    @RequestMapping(value = "/createCreditCard", method = RequestMethod.POST)
    public String createCreditCard(@RequestBody CreditCardRequest creditCardRequest)throws ParseException {
        try {
            creditCardService.CreateNewCard(creditCardRequest);
            return " CreditCard created Successfully ";
        } catch (Exception e) {

            return "CreditCard creation failed";
        }

    }

    @RequestMapping(value = "/updateCreditCard", method = RequestMethod.POST)
    public String updateCreditCard(@RequestBody CreditCardRequestForUpdate creditCardRequestForUpdate)throws ParseException {
        try {
            creditCardService.updateNewCard(creditCardRequestForUpdate);
            return " CreditCard updated Successfully ";
        } catch (Exception e) {

            return "CreditCard update failed";
        }

    }


    @RequestMapping(value = "/deleteCreditCard", method = RequestMethod.GET)
    public String deleteCreditCard(Integer id){
        try {
            creditCardService.deleteCreditCard(id);
            return " CreditCard deleted Successfully ";
        } catch (Exception e) {

            return "CreditCard deletion failed";
        }

    }
}
