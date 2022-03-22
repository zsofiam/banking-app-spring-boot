package com.example.banking_app.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
public class BankAccountDTO {

    private Long id;

    private String number;

    private BigDecimal balance;

    private Long userId;

    public BankAccountDTO() {
    }
}
