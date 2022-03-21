package com.example.banking_app.service;

import com.example.banking_app.model.BankAccount;
import com.example.banking_app.model.Deposit;
import com.example.banking_app.repository.BankAccountRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountService {

    private final BankAccountRepository bankAccountRepository;

    public AccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public List<BankAccount> getAccounts() {
        return bankAccountRepository.findAll();
    }

    public void addAccount(BankAccount account) {
        bankAccountRepository.save(account);
    }

    public BigDecimal getBalance(Long account_id) {
        Optional<BankAccount> bankAccount = bankAccountRepository.findById(account_id);
        return bankAccount.map(BankAccount::getBalance).orElse(null);
    }

    public void depositMoney(Long account_id, Deposit deposit) {
        Optional<BankAccount> bankAccount = bankAccountRepository.findById(account_id);
        BankAccount bankAccount2 = bankAccount.orElseThrow(() -> new RuntimeException("bankaccount not found!"));
        //saját bankaccountnotfoundexception
          //exception handler a controllerben
            bankAccount2.setBalance(BigDecimal.valueOf(10));
            bankAccountRepository.save(bankAccount2);

        BigDecimal currentBalance = bankAccount.map(BankAccount::getBalance).orElse(null);
        BigDecimal newBalance = currentBalance.add(deposit.getAmount());


    }
}
