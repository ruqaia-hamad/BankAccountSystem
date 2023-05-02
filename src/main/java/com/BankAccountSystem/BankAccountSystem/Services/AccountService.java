package com.BankAccountSystem.BankAccountSystem.Services;

import com.BankAccountSystem.BankAccountSystem.Models.Account;
import com.BankAccountSystem.BankAccountSystem.Models.Customer;
import com.BankAccountSystem.BankAccountSystem.Models.Loan;
import com.BankAccountSystem.BankAccountSystem.Models.Transaction;
import com.BankAccountSystem.BankAccountSystem.Repositories.AccountRepository;
import com.BankAccountSystem.BankAccountSystem.Repositories.CustomerRepository;
import com.BankAccountSystem.BankAccountSystem.Repositories.LoanRepository;
import com.BankAccountSystem.BankAccountSystem.Repositories.TransactionRepository;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.AccountRequest;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.AccountRequestForUpdate;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.TransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service

public class AccountService {


    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    TransactionRepository transactionRepository;

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

    public List<Account> getAccountsByCustomer(Customer customer) {
        return accountRepository.findByCustomer(customer);
    }
    public void updateBalance(TransactionRequest transactionRequest) {
        Integer accountId = transactionRequest.getAccountId();
        Double amount = transactionRequest.getAmount();
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + accountId));
        Double balance = account.getAmount() + amount;
        account.setAmount(balance);
        accountRepository.save(account);
    }


    public Double calculateInterestForAccount(Integer accountId) {
        Account account = accountRepository.getAccountById(accountId);
        Customer customer = account.getCustomer();
        Loan loan = loanRepository.findActiveLoansByCustomer(customer);
        if (loan == null) {
            throw new ResourceNotFoundException("Active loan not found for customer: " + customer.getId());
        }
        Double interestRate = loan.getInsertRate();
        Double balance = account.getAmount();
        Double interest = balance * interestRate;
        return interest;
    }


    public String generateMonthlyStatement(Integer accountId) {
        Account account = accountRepository.getAccountById(accountId);
        Customer customer = account.getCustomer();
        String statementForAccount = "Monthly Statement for Account: " + account.getAccountNumber() + "\n" +
                "Customer Name: " + customer.getCustomerName() + "\n" +
                "Month: " + customer.getCreatedDate().getMonth()+ "\n" +
                "Customer Email: " + customer.getEmail() + "\n" +
                "Customer Phone: " + customer.getPhoneNumber() + "\n" +
                "Account Balance: " + account.getAmount() + "\n" +
                "Interest : " + calculateInterestForAccount(accountId) + "\n";
        return statementForAccount;
    }



    public List<Transaction> getAccountHistory(Integer accountId) {
        Account account = accountRepository.getAccountById(accountId);
        List<Transaction> transactions = transactionRepository.findByAccount(account);
        return transactions;
    }

}
