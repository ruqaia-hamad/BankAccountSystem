package com.BankAccountSystem.BankAccountSystem.Repositories;


import com.BankAccountSystem.BankAccountSystem.Models.CreditCard;
import com.BankAccountSystem.BankAccountSystem.Models.CreditCardPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditCardPaymentRepository extends JpaRepository<CreditCardPayment, Integer> {


    @Query(value = "SELECT m FROM CreditCardPayment m")
    List<CreditCardPayment> getAllCreditCards();
}
