package com.example.banking_app.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Withdraw {

    private BigDecimal amount;

    public Withdraw() {
    }
}
