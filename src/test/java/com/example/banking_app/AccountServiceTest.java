package com.example.banking_app;

import com.example.banking_app.exception.AccountNotFoundException;
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
    public void getBalanceOfAccount2GivesReturnsCorrectValue() {
        BigDecimal balance = accountService.getBalance(2L);
        Assertions.assertEquals(new BigDecimal("200000.00"), balance);
    }

    @Test
    public void getBalanceOfNonExistingAccountThrowsException() {
        Long requestedAccountId = 222L;
        Exception exception = Assertions.assertThrows(AccountNotFoundException.class, () -> {
            accountService.getBalance(requestedAccountId);
        });
        String expectedMessage = "Could not find account " + requestedAccountId;
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
