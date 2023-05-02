package com.BankAccountSystem.BankAccountSystem.Services;


import com.BankAccountSystem.BankAccountSystem.Models.Account;
import com.BankAccountSystem.BankAccountSystem.Models.Customer;
import com.BankAccountSystem.BankAccountSystem.Models.Transaction;
import com.BankAccountSystem.BankAccountSystem.Repositories.AccountRepository;
import com.BankAccountSystem.BankAccountSystem.Repositories.CustomerRepository;
import com.BankAccountSystem.BankAccountSystem.Repositories.TransactionRepository;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.AccountRequest;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.TransactionRequest;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.TransactionRequestForUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String sender;

    public void CreateNewTransaction(TransactionRequest transactionRequest) throws ParseException {
        Transaction transaction = new Transaction();
        transaction.setAmount(transactionRequest.getAmount());
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date convetedDate = formatter.parse(transactionRequest.getTransactionDate());
        transaction.setTransactionDate(convetedDate);
        transaction.setIsActive(transactionRequest.getIsActive());
        Account account = accountRepository.getAccountById(transactionRequest.getAccountId());
        transaction.setAccount(account);
        transactionRepository.save(transaction);


        Double accountBalance = account.getAmount();
        if (accountBalance < transaction.getAmount()) {

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            Integer customerId = accountRepository.getAccountByIdForCustomer(transactionRequest.getAccountId());
            String customerMAil = customerRepository.getMailById(customerId);
            mailMessage.setTo(customerMAil);
            mailMessage.setText("We are sorry to inform you that your recent transaction has failed due to insufficient funds in your account.\n\n" +
                    "Your account balance is: " + accountBalance + "\n" +
                    "You tried to send: " + transaction.getAmount() + "\n\n" +
                    "Please deposit more funds into your account and try again.\n\n" +
                    "Thank you for using BANK ABC.");
            mailMessage.setSubject("Bank ABC Notification: Transaction Failed");
            mailSender.send(mailMessage);

            return;
        }
        account.setAmount(accountBalance - transaction.getAmount());
        accountRepository.save(account);

        try {

            Double balanceBeforeTransaction = transaction.getAccount().getAmount() + transaction.getAmount();
            Double balanceAfterTransaction = transaction.getAccount().getAmount();

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            Integer customerId = accountRepository.getAccountByIdForCustomer(transactionRequest.getAccountId());
            String customerMAil = customerRepository.getMailById(customerId);
            mailMessage.setTo(customerMAil);
            mailMessage.setText("Thank you for using BANK ABC for your transaction.\n\n" +
                    "Your balance before the transaction was: " + balanceBeforeTransaction + "\n" +
                    "You sent: " + transaction.getAmount() + "\n" +
                    "Your balance after the transaction is: " + balanceAfterTransaction + "\n\n" +
                    "Please contact us if you have any questions or concerns.");
            mailMessage.setSubject("Bank ABC Notification");

            mailSender.send(mailMessage);

        } catch (Exception e) {
        }


    }

    public void updateTransaction(TransactionRequestForUpdate transactionRequestForUpdate) throws ParseException {
        Transaction transaction = new Transaction();
        transaction.setId(transactionRequestForUpdate.getId());
        transaction.setAmount(transactionRequestForUpdate.getAmount());
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date convetedDate = formatter.parse(transactionRequestForUpdate.getTransactionDate());
        transaction.setTransactionDate(convetedDate);
        transaction.setIsActive(transactionRequestForUpdate.getIsActive());
        Account account = accountRepository.getAccountById(transactionRequestForUpdate.getAccountId());
        transaction.setAccount(account);
        transactionRepository.save(transaction);


    }

    public void deleteTransaction(Integer id) {
        transactionRepository.deleteTransaction(id);
    }
}
