package com.example.banking_app.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class BankAccountDTO {

    private Long id;

    private String number;

    private BigDecimal balance;

    private Long userId;

    public BankAccountDTO() {
    }

    public BankAccountDTO(String s, BigDecimal bigDecimal, Long aLong) {
        this.number = s;
        this.balance = bigDecimal;
        this.userId = aLong;
    }

    public BankAccountDTO(Long id, String number, BigDecimal balance, Long userId) {
        this.id = id;
        this.number = number;
        this.balance = balance;
        this.userId = userId;
    }
}
