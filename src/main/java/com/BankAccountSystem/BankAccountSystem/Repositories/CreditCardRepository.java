package com.BankAccountSystem.BankAccountSystem.Repositories;

import com.BankAccountSystem.BankAccountSystem.Models.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository  extends JpaRepository<CreditCard, Integer> {
}
