package com.example.banking_app.controller;


import com.example.banking_app.dto.BankAccountDTO;
import com.example.banking_app.model.BankAccount;
import com.example.banking_app.model.Deposit;
import com.example.banking_app.model.Transfer;
import com.example.banking_app.model.Withdraw;
import com.example.banking_app.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.net.URI;
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
    public ResponseEntity<Object> addAccount(@RequestBody BankAccountDTO accountDTO) {
        BankAccount bankAccount = accountService.addAccount(accountDTO);
        Long id = bankAccount.getId();
        return ResponseEntity.created(URI.create("/api/v1/account/" + id)).build();
    }

    @GetMapping("/{account_id}/balance")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable("account_id") Long account_id) {
        return new ResponseEntity<>(accountService.getBalance(account_id), HttpStatus.OK);
    }

    @PutMapping("/{account_id}/deposit")
    public ResponseEntity<Object> depositMoney(@PathVariable("account_id") Long account_id, @RequestBody Deposit deposit) {
        accountService.depositMoney(account_id, deposit);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{account_id}/withdraw")
    public ResponseEntity<Object> withdrawMoney(@PathVariable("account_id") Long account_id, @RequestBody Withdraw withdraw) {
        accountService.withdrawMoney(account_id, withdraw);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{account_id}/transfer")
    public ResponseEntity<Object> transferMoney(@PathVariable("account_id") Long account_id, @RequestBody Transfer transfer) {
        accountService.transferMoney(account_id, transfer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
