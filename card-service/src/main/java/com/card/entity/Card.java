package com.card.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Card {

    @Id
    @GeneratedValue
    private Long id;
    private Long number;
    private String name;
    private Date date;
    private TypeCurrency typeOfCurrency;

    public Card(String name, Date date, TypeCurrency typeOfCurrency) {
        String numberCard = String.format("%0%d", this.id) + "" + (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        this.number = Long.parseLong(numberCard);
        this.name = name;
        this.date = date;
        this.typeOfCurrency = typeOfCurrency;
    }
}
