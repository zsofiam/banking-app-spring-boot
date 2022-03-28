package com.example.banking_app.controller;


import com.example.banking_app.dto.BankAccountDTO;
import com.example.banking_app.model.BankAccount;
import com.example.banking_app.dto.Deposit;
import com.example.banking_app.dto.Transfer;
import com.example.banking_app.dto.Withdraw;
import com.example.banking_app.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("")
    public ResponseEntity<List<BankAccountDTO>> getAccounts() {
        return new ResponseEntity<>(accountService.getAccounts(), HttpStatus.OK);
    }

    @GetMapping("/{account_id}")
    public ResponseEntity<BankAccountDTO> getBankAccount(@PathVariable("account_id") Long account_id) {
        return new ResponseEntity<>(accountService.getBankAccountDTO(account_id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<BankAccountDTO> addAccount(@RequestBody BankAccountDTO accountDTO) {
        BankAccount bankAccount = accountService.addAccount(accountDTO);
        Long id = bankAccount.getId();
        String number = bankAccount.getNumber();
        BigDecimal balance = bankAccount.getBalance();
        Long userId = bankAccount.getUser().getId();
        BankAccountDTO bankAccountDTO = new BankAccountDTO(id, number, balance, userId);
//        return ResponseEntity.created(URI.create("/api/v1/account/" + id)).build();
        return new ResponseEntity<>(bankAccountDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{account_id}/balance")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable("account_id") Long account_id) {
        return new ResponseEntity<>(accountService.getBalance(account_id), HttpStatus.OK);
    }

    @PutMapping("/{account_id}/deposit")
    public ResponseEntity<BankAccountDTO> depositMoney(@PathVariable("account_id") Long account_id, @RequestBody Deposit deposit) {
        BankAccountDTO bankAccountDTO = accountService.depositMoney(account_id, deposit);
        return new ResponseEntity<>(bankAccountDTO, HttpStatus.OK);
    }

    @PutMapping("/{account_id}/withdraw")
    public ResponseEntity<BankAccountDTO> withdrawMoney(@PathVariable("account_id") Long account_id, @RequestBody Withdraw withdraw) {
        BankAccountDTO bankAccountDTO = accountService.withdrawMoney(account_id, withdraw);
        return new ResponseEntity<>(bankAccountDTO, HttpStatus.OK);
    }

    @PutMapping("/{account_id}/transfer")
    public ResponseEntity<BankAccountDTO> transferMoney(@PathVariable("account_id") Long account_id, @RequestBody Transfer transfer) {
        BankAccountDTO bankAccountDTO = accountService.transferMoney(account_id, transfer);
        return new ResponseEntity<>(bankAccountDTO, HttpStatus.OK);
    }



}
