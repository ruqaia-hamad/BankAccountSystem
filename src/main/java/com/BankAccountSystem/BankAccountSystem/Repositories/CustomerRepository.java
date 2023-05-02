package com.BankAccountSystem.BankAccountSystem.Repositories;

import com.BankAccountSystem.BankAccountSystem.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer, Integer> {


    @Query(value = "SELECT m FROM Customer m where m.id= :customerId")
    Customer getCustomerById(@Param("customerId") Integer id);

    @Query(value = "  select email from customer where id=:customerId", nativeQuery = true)
    String getMailById(@Param("customerId") Integer customerId);

    @Modifying
    @Transactional
    @Query("UPDATE Customer c SET c.isActive=0  WHERE c.id = :id")
    void deleteCustomer(@Param("id") Integer id);


    @Query(value = "SELECT m FROM Customer m")
    List<Customer> getAllCustomers();
}
