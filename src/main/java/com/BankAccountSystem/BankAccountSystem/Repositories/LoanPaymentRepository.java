package com.BankAccountSystem.BankAccountSystem.Repositories;

import com.BankAccountSystem.BankAccountSystem.Models.CreditCardPayment;
import com.BankAccountSystem.BankAccountSystem.Models.LoanPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanPaymentRepository extends JpaRepository<LoanPayment, Integer> {

    @Query(value = "SELECT m FROM LoanPayment m")
    List<LoanPayment> getAllLoan();
}
