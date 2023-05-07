package com.BankAccountSystem.BankAccountSystem.Services;

import com.BankAccountSystem.BankAccountSystem.DTO.AccountCustomerDTO;
import com.BankAccountSystem.BankAccountSystem.Models.Account;
import com.BankAccountSystem.BankAccountSystem.Repositories.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {
    public static final String pathToReports = "C:\\Users\\user015\\Documents\\Reports";

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    TransactionRepository transactionRepository;


    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    CustomerRepository customerRepository;


    public String generateReportForAccount() throws FileNotFoundException, JRException {
        List<Account> findAccountWithCustomerId = accountRepository.getAllAccounts();
        List<AccountCustomerDTO> customerReports = new ArrayList<>();
        for (Account account : findAccountWithCustomerId) {
            account.getId();
            account.getAccountNumber();
            account.getAmount();
            account.getCustomer().getId();
            account.getCreatedDate();
            AccountCustomerDTO accountsReport = new AccountCustomerDTO( account.getId(),account.getAmount(), account.getAccountNumber(),account.getCreatedDate(),account.getCustomer().getId());
            customerReports.add(accountsReport);
        }
        File file = new File("C:\\Users\\user015\\Documents\\BankAccountSystem\\src\\main\\resources\\AccountReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(customerReports);
        Map<String, Object> paramters = new HashMap<>();
        paramters.put("CreatedBy", "Ruqiya");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, paramters, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, pathToReports + "\\Account.pdf");
        return "Report generated : " + pathToReports + "\\Account.pdf";
    }










}
