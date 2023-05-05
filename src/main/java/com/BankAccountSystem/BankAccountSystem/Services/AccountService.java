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
import java.time.LocalDate;
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


    public Account calculateInterestForAccount(Integer accountId, Double interestRate) {
        Account account = accountRepository.getAccountById(accountId);
        Double currentBalance = account.getAmount();
        Double interestCalculation= currentBalance * interestRate;
        Double newBalance=currentBalance+interestCalculation;
        account.setAmount(newBalance);
        accountRepository.save(account);
        return account;
    }



    public String generateMonthlyStatement(Integer accountId) {
        Account account = accountRepository.getAccountById(accountId);
        Customer customer = account.getCustomer();
        LocalDate currentDate = LocalDate.now();
        LocalDate statementDate = LocalDate.of(currentDate.getYear(), currentDate.getMonth(), 1);

        String statementForAccount = "Monthly Statement for Account: " + account.getAccountNumber() + "\n" +
                "Customer Name: " + customer.getCustomerName() + "\n" +
                "Month: " + statementDate.getMonth().toString() + " " + statementDate.getYear() + "\n" +
                "Customer Email: " + customer.getEmail() + "\n" +
                "Customer Phone: " + customer.getPhoneNumber() + "\n" +
                "Account Balance: " + account.getAmount() + "\n" ;

        return statementForAccount;
    }


    public List<Transaction> getAccountHistory(Integer accountId) {
        Account account = accountRepository.getAccountById(accountId);
        List<Transaction> transactions = transactionRepository.findByAccount(account);
        return transactions;
    }

}
