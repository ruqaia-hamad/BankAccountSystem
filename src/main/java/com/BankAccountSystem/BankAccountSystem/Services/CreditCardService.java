package com.BankAccountSystem.BankAccountSystem.Services;


import com.BankAccountSystem.BankAccountSystem.Models.*;
import com.BankAccountSystem.BankAccountSystem.Repositories.CreditCardPaymentRepository;
import com.BankAccountSystem.BankAccountSystem.Repositories.CreditCardRepository;
import com.BankAccountSystem.BankAccountSystem.Repositories.CustomerRepository;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.AccountRequest;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.CreditCardRequest;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.CreditCardRequestForUpdate;
import com.BankAccountSystem.BankAccountSystem.Slack.SlackClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class CreditCardService {

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CreditCardPaymentRepository creditCardPaymentRepository;

    @Autowired
    SlackClient slackClient;

    public void CreateNewCard(CreditCardRequest creditCardRequest) throws ParseException {
        CreditCard creditCard=new CreditCard();
        creditCard.setCardNumber(creditCardRequest.getCardNumber());
        creditCard.setCreditLimit(creditCardRequest.getCreditLimit());
        creditCard.setCreatedDate(new Date());
        creditCard.setIsActive(creditCardRequest.getIsActive());
        Customer customer = customerRepository.getCustomerById(creditCardRequest.getCustomerId());
        creditCard.setCustomer(customer);
        creditCardRepository.save(creditCard);

    }

    public void updateNewCard(CreditCardRequestForUpdate creditCardRequestForUpdate) throws ParseException {
        CreditCard creditCard=new CreditCard();
        creditCard.setId(creditCardRequestForUpdate.getId());
        creditCard.setCardNumber(creditCardRequestForUpdate.getCardNumber());
        creditCard.setCreditLimit(creditCardRequestForUpdate.getCreditLimit());
        creditCard.setIsActive(creditCardRequestForUpdate.getIsActive());
        Customer customer = customerRepository.getCustomerById(creditCardRequestForUpdate.getCustomerId());
        creditCard.setCustomer(customer);
        creditCardRepository.save(creditCard);

    }

    public void deleteCreditCard(Integer id) {
       creditCardRepository.deleteCreditCard(id);
    }




    public CreditCard getCreditCardById(Integer creditCardId) {
        return creditCardRepository.getCreditCardById(creditCardId);
    }

    public List<CreditCard> getCreditCardByCustomerId(Integer id) {
        return creditCardRepository.findByCustomerId(id);

    }

    public CreditCard makePayment(Integer creditCardId, Double paymentAmount) {
        CreditCard creditCard = creditCardRepository.getCreditCardById(creditCardId);
        Double newBalance = creditCard.getCreditLimit() - paymentAmount;
        if (newBalance < 0) {
            throw new ResourceNotFoundException("Payment amount cannot exceed current balance");
        }

        creditCard.setCreditLimit(newBalance);
        creditCardRepository.save(creditCard);
        CreditCardPayment creditCardPayment = new CreditCardPayment();
        creditCardPayment.setCreditCard(creditCard);
        creditCardPayment.setPaymentAmount(paymentAmount);
        creditCardPayment.setPaymentDate(LocalDate.now());
        creditCardPaymentRepository.save(creditCardPayment);
        return creditCard;
    }

    public CreditCard applyInterest(Integer creditCardId, Double interestRate) {

        CreditCard creditCard = creditCardRepository.getCreditCardById(creditCardId);
        Double currentBalance = creditCard.getCreditLimit();
        Double interestCalculation = currentBalance * interestRate;
        Double newBalance = currentBalance + interestCalculation;
        creditCard.setCreditLimit(newBalance);
        creditCardRepository.save(creditCard);
        return creditCard;
    }



    public CreditCard approveOrRejectCreditCard(Integer creditCardId, double annualIncome) {
        CreditCard creditCard =creditCardRepository.getCreditCardById(creditCardId);

        if (annualIncome >= 600) {
            creditCard.setStatus("approved");
            slackClient.sendMessage("New laon application approved - Loan ID: " + creditCardId);
        } else {
            creditCard.setStatus("rejected");
            slackClient.sendMessage("New loan application rejected - Loan ID: " + creditCard);
        }

        creditCardRepository.save(creditCard);
        return creditCard;
    }

}
