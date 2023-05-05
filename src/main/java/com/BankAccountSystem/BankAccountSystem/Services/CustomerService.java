package com.BankAccountSystem.BankAccountSystem.Services;

import com.BankAccountSystem.BankAccountSystem.Models.Account;
import com.BankAccountSystem.BankAccountSystem.Models.Customer;
import com.BankAccountSystem.BankAccountSystem.Repositories.AccountRepository;
import com.BankAccountSystem.BankAccountSystem.Repositories.CustomerRepository;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.CustomerRequestForCreateCustomer;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.CustomerRequestForUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AccountRepository accountRepository;


    public void CreateNewCustomer(CustomerRequestForCreateCustomer customerInfo) throws ParseException {
        Customer customer = new Customer();
        customer.setCustomerName(customerInfo.getCustomerName());
        customer.setPhoneNumber(customerInfo.getPhoneNumber());
        customer.setEmail(customerInfo.getEmail());
        customer.setCreatedDate(new Date());
        customer.setIsActive(customerInfo.getIsActive());
        customerRepository.save(customer);

    }
    public void updateNewCustomer(CustomerRequestForUpdate customerRequestForUpdate) throws ParseException {
        Customer customer = new Customer();
        customer.setId(customerRequestForUpdate.getId());
        customer.setCustomerName(customerRequestForUpdate.getCustomerName());
        customer.setPhoneNumber(customerRequestForUpdate.getPhoneNumber());
        customer.setEmail(customerRequestForUpdate.getEmail());
        customer.setIsActive(customerRequestForUpdate.getIsActive());
        customerRepository.save(customer);

    }


    public void deleteCustomer(Integer id) {
        customerRepository.deleteCustomer(id);
    }


    public List<Account> getCustomerAccounts(Integer customerId) {
        Customer customer = customerRepository.getCustomerById(customerId);
        List<Account> accounts = customerRepository.getAccountsByCustomer(customer);
        for (Account account : accounts) {
            account.getCustomer().getCustomerName();
            account.getCustomer().getEmail();
            account.getCustomer().getPhoneNumber();
            account.getId();
            account.getAccountNumber();
            account.getAmount();
        }
        return accounts;
    }

}
