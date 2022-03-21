package com.example.banking_app.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

/*@Builder*/
@Getter
@Setter
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    private String comment;

    public Transaction() {

    }

//    @OneToOne(cascade = CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private BankAccount account;

}
