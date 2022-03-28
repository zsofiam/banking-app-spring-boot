package com.example.banking_app.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class Withdraw {

    private BigDecimal amount;

    public Withdraw() {
    }

    public Withdraw(BigDecimal amount) {
        this.amount = amount;
    }
}
