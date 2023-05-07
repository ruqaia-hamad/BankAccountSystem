package com.BankAccountSystem.BankAccountSystem.Services;

import com.BankAccountSystem.BankAccountSystem.DTO.AccountCustomerDTO;
import com.BankAccountSystem.BankAccountSystem.DTO.AccountTransactionDTO;
import com.BankAccountSystem.BankAccountSystem.DTO.CreditCardPaymentDTO;
import com.BankAccountSystem.BankAccountSystem.DTO.LoanPaymentDTO;
import com.BankAccountSystem.BankAccountSystem.Models.*;
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

    @Autowired
    CreditCardPaymentRepository creditCardPaymentRepository;

    @Autowired
    LoanPaymentRepository loanPaymentRepository;


    public String generateReportForAccount() throws FileNotFoundException, JRException {
        List<Account> findAccountWithCustomerId = accountRepository.getAllAccounts();
        List<AccountCustomerDTO> customerReports = new ArrayList<>();
        for (Account account : findAccountWithCustomerId) {
            account.getId();
            account.getAccountNumber();
            account.getAmount();
            account.getCustomer().getId();
            account.getCreatedDate();
            AccountCustomerDTO accountsReport = new AccountCustomerDTO(account.getId(), account.getAmount(), account.getAccountNumber(), account.getCreatedDate(), account.getCustomer().getId());
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


    public String generateReportForTransactions() throws FileNotFoundException, JRException {
        List<Transaction> findAllTransactions = transactionRepository.getAllTransactions();
        List<AccountTransactionDTO> transactionDTOS = new ArrayList<>();
        for (Transaction transaction : findAllTransactions) {
            transaction.getId();
            transaction.getAccount().getId();
            transaction.getAmount();
            transaction.getTransactionDate();
            AccountTransactionDTO transactionDTO = new AccountTransactionDTO(transaction.getId(), transaction.getAccount().getId(), transaction.getAmount(), transaction.getTransactionDate());
            transactionDTOS.add(transactionDTO);
        }
        File file = new File("C:\\Users\\user015\\Documents\\BankAccountSystem\\src\\main\\resources\\AccountTransactions.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(transactionDTOS);
        Map<String, Object> paramters = new HashMap<>();
        paramters.put("CreatedBy", "Ruqiya");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, paramters, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, pathToReports + "\\AccountTransactions.pdf");
        return "Report generated : " + pathToReports + "\\AccountTransactions.pdf";
    }


    public String generateReportForCreditCards() throws FileNotFoundException, JRException {
        List<CreditCardPayment> findAllCreditCardPayments = creditCardPaymentRepository.getAllCreditCards();
        List<CreditCardPaymentDTO> creditCardPaymentDTOs = new ArrayList<>();

        for (CreditCardPayment creditCardPayment : findAllCreditCardPayments) {
            CreditCard creditCard = creditCardPayment.getCreditCard();

            CreditCardPaymentDTO creditCardPaymentDTO = new CreditCardPaymentDTO(
                    creditCardPayment.getId(),
                    creditCard.getId(),
                    creditCard.getCardNumber(),
                    creditCard.getCreditLimit(),
                    creditCardPayment.getPaymentAmount(),
                    creditCardPayment.getPaymentDate()
            );

            creditCardPaymentDTOs.add(creditCardPaymentDTO);
        }

        File file = new File("C:\\Users\\user015\\Documents\\BankAccountSystem\\src\\main\\resources\\CreditCardReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(creditCardPaymentDTOs);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("CreatedBy", "Ruqiya");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, pathToReports + "\\CreditCardPaymentReport.pdf");

        return "Report generated: " + pathToReports + "\\CreditCardPaymentReport.pdf";
    }

    public String generateReportForLoanPayment() throws FileNotFoundException, JRException {
        List<LoanPayment> findAllLoan = loanPaymentRepository.getAllLoan();
        List<LoanPaymentDTO> loanPaymentDTOS = new ArrayList<>();

        for (LoanPayment loanPayment : findAllLoan) {
            Loan loanPayment1 = loanPayment.getLoan();

            LoanPaymentDTO loanPaymentDTO = new LoanPaymentDTO(
                    loanPayment.getId(),
                    loanPayment1.getId(),
                    loanPayment1.getAmount(),
                    loanPayment.getPaymentAmount(),
                    loanPayment.getPaymentDate()

            );

    loanPaymentDTOS.add(loanPaymentDTO);
        }

        File file = new File("C:\\Users\\user015\\Documents\\BankAccountSystem\\src\\main\\resources\\LoanPaymentReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(loanPaymentDTOS);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("CreatedBy", "Ruqiya");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, pathToReports + "\\LoanPaymentReport.pdf");

        return "Report generated: " + pathToReports + "\\LoanPaymentReport.pdf";
    }


}
