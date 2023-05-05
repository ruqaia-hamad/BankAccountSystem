package com.BankAccountSystem.BankAccountSystem.Repositories;

import com.BankAccountSystem.BankAccountSystem.Models.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CreditCardRepository  extends JpaRepository<CreditCard, Integer> {


    @Modifying
    @Transactional
    @Query("UPDATE CreditCard c SET c.isActive=0  WHERE c.id = :id")
    void deleteCreditCard(@Param("id") Integer id);

    @Query(value = "SELECT m FROM CreditCard m where m.id= :creditId")
    CreditCard getCreditCardById(@Param("creditId") Integer id);

    List<CreditCard> findByCustomerId(Integer customerId);


}
