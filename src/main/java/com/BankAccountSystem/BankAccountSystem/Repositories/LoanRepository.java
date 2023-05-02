package com.BankAccountSystem.BankAccountSystem.Repositories;

import com.BankAccountSystem.BankAccountSystem.Models.Customer;
import com.BankAccountSystem.BankAccountSystem.Models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {


    @Modifying
    @Transactional
    @Query("UPDATE Loan c SET c.isActive=0  WHERE c.id = :id")
    void deleteLoan(@Param("id") Integer id);


    @Query("SELECT l FROM Loan l WHERE l.customer = :customer AND l.isActive = 'TRUE'")
    Loan findActiveLoansByCustomer(@Param("customer") Customer customer);


    @Query(value = "\n" +
            "select * from loan where customer_id=:customerId", nativeQuery = true)
    List<Loan> getLoansByCustomerId(@Param("customerId") Integer customerId);
}
