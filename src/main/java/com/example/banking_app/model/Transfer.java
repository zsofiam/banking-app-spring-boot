package com.example.banking_app.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Transfer {

    private BigDecimal amount;

    private String destinationAccountNumber;

    public Transfer() {
    }
}
