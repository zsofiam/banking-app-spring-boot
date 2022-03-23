package com.example.banking_app.repository;

import com.example.banking_app.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    Optional<BankAccount> findById(Long id);
    Optional<BankAccount> findByNumber(String accountNumber);
    List<BankAccount> findAll();

}