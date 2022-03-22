package com.example.banking_app.service;

import com.example.banking_app.dto.BankAccountDTO;
import com.example.banking_app.exception.AccountNotFoundException;
import com.example.banking_app.model.BankAccount;
import com.example.banking_app.model.Deposit;
import com.example.banking_app.model.Transfer;
import com.example.banking_app.model.Withdraw;
import com.example.banking_app.repository.BankAccountRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountService {

    private final BankAccountRepository bankAccountRepository;

    public AccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public List<BankAccountDTO> getAccounts() {
        return bankAccountRepository.findAll()
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    private BankAccountDTO convertEntityToDTO(BankAccount bankAccount) {
        BankAccountDTO bankAccountDTO = new BankAccountDTO();

        bankAccountDTO.setId(bankAccount.getId());
        bankAccountDTO.setNumber(bankAccount.getNumber());
        bankAccountDTO.setBalance(bankAccount.getBalance());
        bankAccountDTO.setUserId(bankAccount.getUser().getId());

        return bankAccountDTO;
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
        BankAccount bankAccount2 = bankAccount.orElseThrow(() -> new RuntimeException("bankaccount not found " + account_id));
        //saját bankaccountnotfoundexception
          //exception handler a controllerben
            bankAccount2.setBalance(bankAccount2.getBalance().add(deposit.getAmount()));
            bankAccountRepository.save(bankAccount2);

        BigDecimal currentBalance = bankAccount.map(BankAccount::getBalance).orElse(null);
        BigDecimal newBalance = currentBalance.add(deposit.getAmount());


    }

    public void withdrawMoney(Long account_id, Withdraw withdraw) {
        Optional<BankAccount> bankAccount = bankAccountRepository.findById(account_id);
        BankAccount bankAccountFound = bankAccount.orElseThrow(() -> new RuntimeException("bankaccount not found " + account_id));
        if ((bankAccountFound.getBalance().subtract(withdraw.getAmount())).compareTo(BigDecimal.ZERO) >= 0) {
            bankAccountFound.setBalance(bankAccountFound.getBalance().subtract(withdraw.getAmount()));
            bankAccountRepository.save(bankAccountFound);
        }
        else{
            throw new RuntimeException("Not enough money on your account!");
        }

    }

    public void transferMoney(Long account_id, Transfer transfer) {
        Optional<BankAccount> bankAccount = bankAccountRepository.findById(account_id);
        Optional<BankAccount> destinationBankAccount = bankAccountRepository.findByNumber(transfer.getDestinationAccountNumber());
        BankAccount bankAccountFound = bankAccount.orElseThrow(() -> new AccountNotFoundException("bank account not found " + account_id));
        BankAccount bankAccountDestination = destinationBankAccount.orElseThrow(() -> new RuntimeException("bank account not found " + account_id));
        if ((bankAccountFound.getBalance().subtract(transfer.getAmount())).compareTo(BigDecimal.ZERO) >= 0) {
            bankAccountFound.setBalance(bankAccountFound.getBalance().subtract(transfer.getAmount()));
            bankAccountDestination.setBalance(bankAccountDestination.getBalance().add(transfer.getAmount()));
            bankAccountRepository.save(bankAccountFound);
            bankAccountRepository.save(bankAccountDestination);
        }
        else{
            throw new RuntimeException("Not enough money on your account!");
        }
    }

    public BankAccountDTO getBankAccount(Long account_id) {
        Optional<BankAccount> bankAccount = bankAccountRepository.findById(account_id);
        BankAccount bankAccountFound = bankAccount.orElseThrow(() -> new AccountNotFoundException("bank account not found " + account_id));
        return convertEntityToDTO(bankAccountFound);
    }
}
