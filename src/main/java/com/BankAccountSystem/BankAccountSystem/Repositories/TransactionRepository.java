package com.BankAccountSystem.BankAccountSystem.Repositories;


import com.BankAccountSystem.BankAccountSystem.Models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {


    @Modifying
    @Transactional
    @Query("UPDATE Transaction c SET c.isActive=0  WHERE c.id = :id")
    void deleteTransaction(@Param("id") Integer id);
}
