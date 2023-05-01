package com.BankAccountSystem.BankAccountSystem.Services;

import com.BankAccountSystem.BankAccountSystem.Models.Account;
import com.BankAccountSystem.BankAccountSystem.Models.Customer;
import com.BankAccountSystem.BankAccountSystem.Repositories.AccountRepository;
import com.BankAccountSystem.BankAccountSystem.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class AccountService {


    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CustomerRepository customerRepository;

    public void CreateNewAccount(Integer accountNumber, Double amount, Integer customer_id) {
        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setAmount(amount);
        Customer customer = customerRepository.getCustomerById(customer_id);
        account.setCustomer(customer);
        accountRepository.save(account);

    }
}
