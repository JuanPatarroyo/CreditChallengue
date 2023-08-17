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
@Table(name = "credit_card")
public class Card {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private Long number;
    private String name;
    private Date date;
    @OneToOne
    @JoinColumn(name = "type-currency")
    private TypeCurrency typeOfCurrency;

    public Card(String name, Date date, TypeCurrency typeOfCurrency) {
        String base = "000000";
        try {
            long numberCard = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
            this.number = numberCard;
            this.name = name;
            this.date = date;
            this.typeOfCurrency = typeOfCurrency;
        } catch (Exception ex){
            ex.printStackTrace(System.err);
        }
    }

    public Card(Long numberId){
        try{
            this.number = Long.parseLong(numberId+""+(long) Math.floor(Math.random() * 9_000_000_000L));
        } catch (Exception ex){
            ex.printStackTrace(System.err);
        }
    }
}
