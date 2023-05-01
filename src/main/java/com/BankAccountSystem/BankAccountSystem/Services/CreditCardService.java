package com.BankAccountSystem.BankAccountSystem.Services;


import com.BankAccountSystem.BankAccountSystem.Models.Account;
import com.BankAccountSystem.BankAccountSystem.Models.CreditCard;
import com.BankAccountSystem.BankAccountSystem.Models.Customer;
import com.BankAccountSystem.BankAccountSystem.Repositories.CreditCardRepository;
import com.BankAccountSystem.BankAccountSystem.Repositories.CustomerRepository;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.AccountRequest;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.CreditCardRequest;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.CreditCardRequestForUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CreditCardService {

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    CustomerRepository customerRepository;

    public void CreateNewCard(CreditCardRequest creditCardRequest) throws ParseException {
        CreditCard creditCard=new CreditCard();
        creditCard.setCardNumber(creditCardRequest.getCardNumber());
        creditCard.setCreditLimit(creditCardRequest.getCreditLimit());
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date convetedDate = formatter.parse(creditCardRequest.getCreatedDate());
        creditCard.setCreatedDate(convetedDate);
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
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date convetedDate = formatter.parse(creditCardRequestForUpdate.getCreatedDate());
        creditCard.setCreatedDate(convetedDate);
        creditCard.setIsActive(creditCardRequestForUpdate.getIsActive());
        Customer customer = customerRepository.getCustomerById(creditCardRequestForUpdate.getCustomerId());
        creditCard.setCustomer(customer);
        creditCardRepository.save(creditCard);

    }

    public void deleteCreditCard(Integer id) {
       creditCardRepository.deleteCreditCard(id);
    }

}
