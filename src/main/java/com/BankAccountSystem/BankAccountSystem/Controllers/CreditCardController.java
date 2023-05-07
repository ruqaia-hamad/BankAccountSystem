package com.BankAccountSystem.BankAccountSystem.Controllers;


import com.BankAccountSystem.BankAccountSystem.Models.CreditCard;
import com.BankAccountSystem.BankAccountSystem.Models.Loan;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.AccountRequest;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.CreditCardRequest;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.CreditCardRequestForUpdate;
import com.BankAccountSystem.BankAccountSystem.Services.CreditCardService;
import com.BankAccountSystem.BankAccountSystem.Services.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

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

    @PostMapping("/makePayment")
    public ResponseEntity<String> getCreditCardById(@RequestParam Integer creditCardId, Double amount) {
        try {
            CreditCard creditCard = creditCardService.makePayment(creditCardId, amount);

            if (creditCard == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Credit card not found with id: " + creditCardId);
            }
            String message = "Payment made successfully\n" +
                    "Card Number: " + creditCard.getCardNumber() + "\n" +
                    "Payment Amount: " + amount + "\n" +
                    "Credit Card new Limit: " + creditCard.getCreditLimit();
            return ResponseEntity.ok(message);
        } catch (Exception e) {

            String errorMessage = e.getMessage();
            return ResponseEntity.badRequest().body(errorMessage);
        }

    }
    @PostMapping("/calculateCardInterest")
    public ResponseEntity<String> calculateCardInterest(@RequestParam Integer creditCardId,  Double interestRate) {
        try {

            CreditCard creditCard = creditCardService.applyInterest(creditCardId,interestRate);

            if (creditCard == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Credit card not found with id: " + creditCardId);
            }
            String message = "Interest calculation made successfully\n" +
                    "Card Number: " + creditCard.getCardNumber() + "\n" +
                    "Interest Rate: " + interestRate + "\n" +
                    "Credit Card new Balance: " + creditCard.getCreditLimit();
            return ResponseEntity.ok(message);
        } catch (Exception e) {

            String errorMessage = e.getMessage();
            return ResponseEntity.badRequest().body(errorMessage);
        }}


    @GetMapping("/findCreditByCustomer")
    public ResponseEntity<?> getCreditByCustomer(@RequestParam("customerId") Integer customerId) {
        List<CreditCard> creditCards = creditCardService.getCreditCardByCustomerId(customerId);
        if (creditCards.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Credit Card Found for Customer " + customerId);
        }
        return ResponseEntity.ok(creditCards);
    }


    @GetMapping("/getCreditCardByStatus")
    public String getCreditCardByStatus(@RequestParam Integer creditCardId) {
        CreditCard creditCard = creditCardService.getCreditCardById(creditCardId);
        return "The Credit Card Status is :" + creditCard.getStatus();
    }


    @PostMapping("/loanStatus")
    public String approveOrRejectCreditCard(@RequestParam Integer creditCardId, double creditScore) {
        try {
            CreditCard creditCard = creditCardService.approveOrRejectCreditCard(creditCardId, creditScore);
            String message = creditCard.getStatus().equals("approved") ? "Credit Card application approved" : "Credit Card  application rejected";
            return message;
        } catch (ResourceNotFoundException e) {
            return "Credit Card not found";
        } catch (Exception e) {
            return "An error occurred while processing the request";
        }
    }
}
