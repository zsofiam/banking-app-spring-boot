package com.example.banking_app.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class Deposit {

    private BigDecimal amount;

    public Deposit() {
    }
}
