package com.BankAccountSystem.BankAccountSystem.Services;

import com.BankAccountSystem.BankAccountSystem.Models.Customer;
import com.BankAccountSystem.BankAccountSystem.Repositories.CustomerRepository;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.CustomerRequestForCreateCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;


    public void CreateNewCustomer(CustomerRequestForCreateCustomer customerInfo) throws ParseException {
        Customer customer = new Customer();
        customer.setCustomerName(customerInfo.getCustomerName());
        customer.setPhoneNumber(customerInfo.getPhoneNumber());
        customer.setEmail(customerInfo.getEmail());
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date convetedDate = formatter.parse(customerInfo.getCreatedDate());
        customer.setCreatedDate(convetedDate);
        customer.setIsActive(customerInfo.getIsActive());
        customerRepository.save(customer);

    }
}
