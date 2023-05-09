package com.BankAccountSystem.BankAccountSystem.Controllers;

import com.BankAccountSystem.BankAccountSystem.Models.Account;
import com.BankAccountSystem.BankAccountSystem.Models.Customer;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.CustomerRequestForCreateCustomer;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.CustomerRequestForUpdate;
import com.BankAccountSystem.BankAccountSystem.Services.CustomerService;
import com.BankAccountSystem.BankAccountSystem.Slack.SlackClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(value = "Customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    SlackClient slackClient;

    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/updateCustomer", method = RequestMethod.POST)
    public String updateCustomer(@RequestBody CustomerRequestForUpdate customerRequestForUpdate)throws ParseException  {
        try {
            customerService.updateNewCustomer(customerRequestForUpdate);
            return " customer Information updated Successfully ";
        } catch (Exception e) {
            return "update customer Information  failed";
        }

    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/deleteCustomer", method = RequestMethod.GET)
    public String deleteCustomer(Integer id) {
        try {
            customerService.deleteCustomer(id);
            return " customer deleted Successfully ";
        } catch (Exception e) {
            return "customer delete failed";
        }

    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAllCustomerAccounts")
    public List<Account> getCustomerAccounts(@RequestParam Integer customerId) {
        List<Account> accounts = customerService.getCustomerAccounts(customerId);
        return accounts;
    }
}
