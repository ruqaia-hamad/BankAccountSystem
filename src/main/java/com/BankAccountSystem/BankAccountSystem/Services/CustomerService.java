package com.BankAccountSystem.BankAccountSystem.Services;

import com.BankAccountSystem.BankAccountSystem.Models.Customer;
import com.BankAccountSystem.BankAccountSystem.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;


    public void CreateNewCustomer(String customerName, String phone, String email) {
        Customer customer = new Customer();
        customer.setCustomerName(customerName);
        customer.setPhoneNumber(phone);
        customer.setEmail(email);

    }
}
