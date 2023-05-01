package com.BankAccountSystem.BankAccountSystem.Repositories;


import com.BankAccountSystem.BankAccountSystem.Models.Account;
import com.BankAccountSystem.BankAccountSystem.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AccountRepository  extends JpaRepository<Account, Integer> {


    @Query(value = "SELECT m FROM Account m where m.id= :accountId")
    Account getAccountById(@Param("accountId") Integer id);


    @Modifying
    @Transactional
    @Query("UPDATE Account c SET c.isActive=0  WHERE c.id = :id")
    void deleteAccount(@Param("id") Integer id);
}
