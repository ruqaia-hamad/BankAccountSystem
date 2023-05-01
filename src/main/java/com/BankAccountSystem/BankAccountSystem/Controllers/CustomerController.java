package com.BankAccountSystem.BankAccountSystem.Controllers;

import com.BankAccountSystem.BankAccountSystem.Models.Customer;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.CustomerRequestForCreateCustomer;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.CustomerRequestForUpdate;
import com.BankAccountSystem.BankAccountSystem.Services.CustomerService;
import com.BankAccountSystem.BankAccountSystem.Slack.SlackClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping(value = "Customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    SlackClient slackClient;
    @RequestMapping(value = "/createCustomer", method = RequestMethod.POST)
    public String createCustomer(@RequestBody CustomerRequestForCreateCustomer customerRequest)throws ParseException  {
        try {
            customerService.CreateNewCustomer(customerRequest);
            slackClient.sendMessage(" new customer  sign up Successfully ");
            return " new customer  sign up Successfully ";
        } catch (Exception e) {
            slackClient.sendMessage("customer  sign up failed");
            return "customer  sign up failed";
        }

    }

    @RequestMapping(value = "/updateCustomer", method = RequestMethod.POST)
    public String updateCustomer(@RequestBody CustomerRequestForUpdate customerRequestForUpdate)throws ParseException  {
        try {
            customerService.updateNewCustomer(customerRequestForUpdate);
            return " new customer  sign up Successfully ";
        } catch (Exception e) {
            return "customer  sign up failed";
        }

    }


}
