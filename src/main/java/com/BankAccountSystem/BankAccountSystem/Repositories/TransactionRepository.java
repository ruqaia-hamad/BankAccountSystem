package com.BankAccountSystem.BankAccountSystem.Repositories;


import com.BankAccountSystem.BankAccountSystem.Models.Account;
import com.BankAccountSystem.BankAccountSystem.Models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {


    @Modifying
    @Transactional
    @Query("UPDATE Transaction c SET c.isActive=0  WHERE c.id = :id")
    void deleteTransaction(@Param("id") Integer id);


    List<Transaction> findByAccount(Account account);

    @Query(value = "SELECT m FROM Transaction m")
    List<Transaction> getAllTransactions();

    @Query(value = "select * from account_transaction where account_id=:account_id",nativeQuery = true)
    List<Transaction> getTransactionsByAccountId(@Param("account_id") Integer account_id);

}
