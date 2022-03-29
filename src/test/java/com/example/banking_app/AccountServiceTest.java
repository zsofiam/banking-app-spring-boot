package com.example.banking_app;

import com.example.banking_app.dto.Deposit;
import com.example.banking_app.dto.Transfer;
import com.example.banking_app.dto.Withdraw;
import com.example.banking_app.exception.AccountNotFoundException;
import com.example.banking_app.exception.LowBalanceException;
import com.example.banking_app.service.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Test
    public void getBalanceOfAccount1GivesReturnsCorrectValue() {
        BigDecimal balance = accountService.getBalance(1L);
        Assertions.assertEquals(new BigDecimal("100000.00"), balance);
    }

    @Test
    public void getBalanceOfNonExistingAccountThrowsException() {
        Long requestedAccountId = 222L;
        Exception exception = Assertions.assertThrows(AccountNotFoundException.class, () -> accountService.getBalance(requestedAccountId));
        String expectedMessage = "Could not find account " + requestedAccountId;
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void getBalanceOfAccount2AfterDepositGivesReturnsCorrectValue() {
        accountService.depositMoney(2L,new Deposit(new BigDecimal("200000.00")));
        BigDecimal balance = accountService.getBalance(2L);
        Assertions.assertEquals(new BigDecimal("400000.00"), balance);
    }

    @Test
    public void getBalanceOfAccount3AfterWithdrawGivesReturnsCorrectValue() {
        accountService.withdrawMoney(3L,new Withdraw(new BigDecimal("200000.00")));
        BigDecimal balance = accountService.getBalance(3L);
        Assertions.assertEquals(new BigDecimal("100000.00"), balance);
    }

    @Test
    public void getBalanceOfAccount4And5AfterTransferGivesReturnsCorrectValue() {
        accountService.transferMoney(4L,new Transfer(new BigDecimal("100000.00"), "20000002"));
        BigDecimal balance4 = accountService.getBalance(4L);
        Assertions.assertEquals(new BigDecimal("300000.00"), balance4);
        BigDecimal balance5 = accountService.getBalance(5L);
        Assertions.assertEquals(new BigDecimal("600000.00"), balance5);
    }

    @Test
    public void tryingToWithdrawMoneyThatExceedsBalanceThrowsException() {
        Exception exception = Assertions.assertThrows(LowBalanceException.class, () -> {
            accountService.withdrawMoney(6L,new Withdraw(new BigDecimal("600001.00")));
        });
        String expectedMessage = "Not enough money on your account!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
