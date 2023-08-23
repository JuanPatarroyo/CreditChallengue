package com.card.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;
    @JoinColumn(name = "card_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Card cardId;
    @Column(name = "transaction_date")
    private Date transactionDate;
    private Long value;

}
