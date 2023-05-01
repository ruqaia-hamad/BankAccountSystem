package com.BankAccountSystem.BankAccountSystem.Repositories;

import com.BankAccountSystem.BankAccountSystem.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer, Integer> {


    @Query(value = "SELECT m FROM Customer m where m.id= :customerId")
    Customer getCustomerById(@Param("customerId") Integer id);


    @Modifying
    @Transactional
    @Query("UPDATE Customer c SET c.isActive=0  WHERE c.id = :id")
    void deleteCustomer(@Param("id") Integer id);
}
