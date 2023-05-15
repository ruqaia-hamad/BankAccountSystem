package com.BankAccountSystem.BankAccountSystem.Controllers;


import com.BankAccountSystem.BankAccountSystem.Services.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/Report")
public class ReportController {

@Autowired
    ReportService reportService;


    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/AccountReport", method = RequestMethod.GET)
    public String generateReportAccount() throws FileNotFoundException, JRException {

        return reportService.generateReportForAccount();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/AccountTransactionReport", method = RequestMethod.GET)
    public String generateReportAccountTransaction() throws FileNotFoundException, JRException {

        return reportService.generateReportForTransactions();
    }


    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/monthlyReportForAccount", method = RequestMethod.GET)
    public String generateMonthlyReportForAccount(@RequestParam int year, int month,Integer accountId) throws FileNotFoundException, JRException {

        return reportService.generateMonthlyReportForAccount(year,month,accountId);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/creditCardReport", method = RequestMethod.GET)
    public String generateReportCreditCard() throws FileNotFoundException, JRException {

        return reportService.generateReportForCreditCards();
    }



    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/LoanPaymentReport", method = RequestMethod.GET)
    public String generateReportForLoanPayment() throws FileNotFoundException, JRException {

        return reportService.generateReportForLoanPayment();
    }


    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/AccountsTransactionForMonthReport", method = RequestMethod.GET)
    public String generateReportAccountTransactionForMonth(@RequestParam int year,int month) throws FileNotFoundException, JRException {

        return reportService.generateReportForTransactionsInSpecificMonth(year,month);
    }
}

