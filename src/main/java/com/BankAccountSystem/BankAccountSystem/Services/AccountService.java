package com.BankAccountSystem.BankAccountSystem.Services;

import com.BankAccountSystem.BankAccountSystem.Models.Account;
import com.BankAccountSystem.BankAccountSystem.Models.Customer;
import com.BankAccountSystem.BankAccountSystem.Repositories.AccountRepository;
import com.BankAccountSystem.BankAccountSystem.Repositories.CustomerRepository;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.AccountRequest;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.AccountRequestForUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service

public class AccountService {


    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CustomerRepository customerRepository;

    public void CreateNewAccount(AccountRequest accountRequest) throws ParseException {
        Account account = new Account();
        account.setAccountNumber(accountRequest.getAccountNumber());
        account.setAmount(accountRequest.getAmount());
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date convetedDate = formatter.parse(accountRequest.getCreatedDate());
        account.setCreatedDate(convetedDate);
        account.setIsActive(accountRequest.getIsActive());
        Customer customer = customerRepository.getCustomerById(accountRequest.getCustomerId());
        account.setCustomer(customer);
        accountRepository.save(account);

    }



    public void updateAccount(AccountRequestForUpdate accountRequestForUpdate) throws ParseException {
        Account account = new Account();
        account.setId(accountRequestForUpdate.getId());
        account.setAccountNumber(accountRequestForUpdate.getAccountNumber());
        account.setAmount(accountRequestForUpdate.getAmount());
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date convetedDate = formatter.parse(accountRequestForUpdate.getCreatedDate());
        account.setCreatedDate(convetedDate);
        account.setIsActive(accountRequestForUpdate.getIsActive());
        Customer customer = customerRepository.getCustomerById(accountRequestForUpdate.getCustomerId());
        account.setCustomer(customer);
        accountRepository.save(account);

    }


    public void deleteAccount(Integer id) {
        accountRepository.deleteAccount(id);
    }

}
