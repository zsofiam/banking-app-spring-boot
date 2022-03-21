package com.example.banking_app.controller;


import com.example.banking_app.model.BankAccount;
import com.example.banking_app.model.Deposit;
import com.example.banking_app.service.AccountService;
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
    public List<BankAccount> getAccounts() {
        return accountService.getAccounts();
    }

    @PostMapping("")
    public void addAccount(@RequestBody BankAccount account) {
        accountService.addAccount(account);
    }

    @GetMapping("/{account_id}/balance")
    public BigDecimal getBalance(@PathVariable("account_id") Long account_id) {
        return accountService.getBalance(account_id);
    }

    @PostMapping("/{account_id}/deposit")
    public void depositMoney(@PathVariable("account_id") Long account_id, @RequestBody Deposit deposit) {
        System.out.println(account_id);
        System.out.println(deposit);
//        accountService.depositMoney(account_id, deposit);
    }

   /* @GetMapping("/{user_id}")
    public User getUtilityAccount(@PathVariable("user_id") Long user_id) {
        return userService.getUserById(user_id);
    }

    @PostMapping("")
    public void addAccount(@RequestBody Account account) {
        accountService.addAccount(account);
    }

    @DeleteMapping("/{user_id}")
    public void deleteUser(@PathVariable("user_id") Long user_id) {
        userService.deleteUser(user_id);
    }*/
}
