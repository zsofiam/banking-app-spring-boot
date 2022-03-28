package com.example.banking_app.dto;

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

    public Transfer(BigDecimal amount, String destinationAccountNumber) {
        this.amount = amount;
        this.destinationAccountNumber = destinationAccountNumber;
    }
}
