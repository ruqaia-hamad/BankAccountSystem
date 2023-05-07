package com.BankAccountSystem.BankAccountSystem.Controllers;


import com.BankAccountSystem.BankAccountSystem.Services.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/Report")
public class ReportController {

@Autowired
    ReportService reportService;


//    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/AccountReport", method = RequestMethod.GET)
    public String generateReportAccount() throws FileNotFoundException, JRException {

        return reportService.generateReportForAccount();
    }
}

