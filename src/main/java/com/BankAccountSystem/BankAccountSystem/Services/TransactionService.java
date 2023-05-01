package com.BankAccountSystem.BankAccountSystem.Services;


import com.BankAccountSystem.BankAccountSystem.Models.Account;
import com.BankAccountSystem.BankAccountSystem.Models.Customer;
import com.BankAccountSystem.BankAccountSystem.Models.Transaction;
import com.BankAccountSystem.BankAccountSystem.Repositories.AccountRepository;
import com.BankAccountSystem.BankAccountSystem.Repositories.TransactionRepository;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.AccountRequest;
import com.BankAccountSystem.BankAccountSystem.RequsetObject.TransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
    AccountRepository accountRepository;


    public void CreateNewTransaction(TransactionRequest transactionRequest) throws ParseException {
        Transaction transaction = new Transaction();
        transaction.setAmount(transactionRequest.getAmount());
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date convetedDate = formatter.parse(transactionRequest.getTransactionDate());
        transaction.setTransactionDate(convetedDate);
        Date convetedDate2 = formatter.parse(transactionRequest.getCreatedDate());
        transaction.setCreatedDate(convetedDate2);
        transaction.setIsActive(transactionRequest.getIsActive());
        Account account=accountRepository.getAccountById(transactionRequest.getAccountId());
        transaction.setAccount(account);
        transactionRepository.save(transaction);


    }
}
