package com.example.banking_app.service;

import com.example.banking_app.dto.BankAccountDTO;
import com.example.banking_app.exception.AccountNotFoundException;
import com.example.banking_app.exception.ApiException;
import com.example.banking_app.exception.LowBalanceException;
import com.example.banking_app.model.*;
import com.example.banking_app.repository.BankAccountRepository;
import com.example.banking_app.repository.UserRepository;
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
    private final UserRepository userRepository;

    public AccountService(BankAccountRepository bankAccountRepository, UserRepository userRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.userRepository = userRepository;
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

    public BankAccount addAccount(BankAccountDTO accountDTO) {
        BankAccount bankAccount = convertDTOToEntity(accountDTO);
        return bankAccountRepository.save(bankAccount);
    }

    private BankAccount convertDTOToEntity(BankAccountDTO accountDTO) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(accountDTO.getId());
        bankAccount.setNumber(accountDTO.getNumber());
        bankAccount.setBalance(accountDTO.getBalance());
        Long userId = accountDTO.getUserId();
        Optional<User> user = userRepository.findById(userId);
        User userFound = user.orElseThrow(() -> new ApiException("User not found: " + userId));
        bankAccount.setUser(userFound);
        return bankAccount;
    }

    public BigDecimal getBalance(Long account_id) {
        Optional<BankAccount> bankAccount = bankAccountRepository.findById(account_id);
        return bankAccount.map(BankAccount::getBalance).orElse(null);
    }

    public void depositMoney(Long account_id, Deposit deposit) {
        Optional<BankAccount> bankAccount = bankAccountRepository.findById(account_id);
        BankAccount bankAccountFound = bankAccount.orElseThrow(() -> new AccountNotFoundException("bankaccount not found " + account_id));
        bankAccountFound.setBalance(bankAccountFound.getBalance().add(deposit.getAmount()));
        bankAccountRepository.save(bankAccountFound);
    }

    public void withdrawMoney(Long account_id, Withdraw withdraw) {
        Optional<BankAccount> bankAccount = bankAccountRepository.findById(account_id);
        BankAccount bankAccountFound = bankAccount.orElseThrow(() -> new AccountNotFoundException("bankaccount not found " + account_id));
        if ((bankAccountFound.getBalance().subtract(withdraw.getAmount())).compareTo(BigDecimal.ZERO) >= 0) {
            bankAccountFound.setBalance(bankAccountFound.getBalance().subtract(withdraw.getAmount()));
            bankAccountRepository.save(bankAccountFound);
        }
        else{
            throw new LowBalanceException("Not enough money on your account!");
        }

    }

    public void transferMoney(Long account_id, Transfer transfer) {
        Optional<BankAccount> bankAccount = bankAccountRepository.findById(account_id);
        Optional<BankAccount> destinationBankAccount = bankAccountRepository.findByNumber(transfer.getDestinationAccountNumber());
        BankAccount bankAccountFound = bankAccount.orElseThrow(() -> new AccountNotFoundException("bank account not found " + account_id));
        BankAccount destinationBankAccountFound = destinationBankAccount.orElseThrow(() -> new AccountNotFoundException("bank account not found " + account_id));
        if ((bankAccountFound.getBalance().subtract(transfer.getAmount())).compareTo(BigDecimal.ZERO) >= 0) {
            bankAccountFound.setBalance(bankAccountFound.getBalance().subtract(transfer.getAmount()));
            destinationBankAccountFound.setBalance(destinationBankAccountFound.getBalance().add(transfer.getAmount()));
            bankAccountRepository.save(bankAccountFound);
            bankAccountRepository.save(destinationBankAccountFound);
        }
        else{
            throw new LowBalanceException("Not enough money on your account!");
        }
    }

    public BankAccountDTO getBankAccount(Long account_id) {
        Optional<BankAccount> bankAccount = bankAccountRepository.findById(account_id);
        BankAccount bankAccountFound = bankAccount.orElseThrow(() -> new AccountNotFoundException("bank account not found " + account_id));
        return convertEntityToDTO(bankAccountFound);
    }
}
