package com.example.banking_app.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Deposit {

    private BigDecimal amount;

    public Deposit(BigDecimal amount) {
        this.amount = amount;
    }

    public Deposit() {
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "amount=" + amount +
                '}';
    }
}
