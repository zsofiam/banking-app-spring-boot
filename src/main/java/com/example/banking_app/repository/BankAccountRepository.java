package com.example.banking_app.repository;

import com.example.banking_app.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    Optional<BankAccount> findById(Long id);
    Optional<BankAccount> findByNumber(String accountNumber);

//    BankAccount findById(Long id);

    List<BankAccount> findAll();

    @Modifying
    @Query(value = "update account a set a.balance = ? where a.id = ?",
            nativeQuery = true)
    int updateAccountSetBalanceNative(BigDecimal balance, Long id);
}