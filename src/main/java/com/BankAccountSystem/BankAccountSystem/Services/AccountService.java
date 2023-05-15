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
import java.time.ZoneId;
import java.util.ArrayList;
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
        List<Transaction> allTransactions = transactionRepository.getAllTransactions();
        List<Transaction> transactions = new ArrayList<>();

        for (Transaction transaction : allTransactions) {
            LocalDate transactionDate = transaction.getTransactionDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (transactionDate.getYear() == statementDate.getYear() && transactionDate.getMonth() == statementDate.getMonth()) {
                transactions.add(transaction);
            }
        }

        StringBuilder statementForAccount = new StringBuilder();
        statementForAccount.append("Monthly Statement for Account: ").append(account.getAccountNumber()).append("\n");
        statementForAccount.append("Customer Name: ").append(customer.getCustomerName()).append("\n");
        statementForAccount.append("Month: ").append(statementDate.getMonth().toString()).append(" ").append(statementDate.getYear()).append("\n");
        statementForAccount.append("Customer Email: ").append(customer.getEmail()).append("\n");
        statementForAccount.append("Customer Phone: ").append(customer.getPhoneNumber()).append("\n");
        statementForAccount.append("Account Balance: ").append(account.getAmount()).append("\n");
        statementForAccount.append("Transactions:").append("\n");

        for (Transaction transaction : transactions) {
            statementForAccount.append("Transaction ID: ").append(transaction.getId()).append("\n");
            statementForAccount.append("Transaction Amount: ").append(transaction.getAmount()).append("\n");
            statementForAccount.append("Transaction Date: ").append(transaction.getTransactionDate()).append("\n");
            statementForAccount.append("\n");
        }

        return statementForAccount.toString();
    }


    public List<Transaction> getAccountHistory(Integer accountId) {
        Account account = accountRepository.getAccountById(accountId);
        List<Transaction> transactions = transactionRepository.findByAccount(account);
        return transactions;
    }


    public double getAccountBalance(Integer accountId) {
        Account account = accountRepository.getAccountById(accountId);
        if (account != null) {
            return account.getAmount();
        }
        throw new ResourceNotFoundException("Account not found with ID: " + accountId);
    }
}
