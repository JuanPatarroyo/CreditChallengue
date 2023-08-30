package com.card.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "credit_card")
public class Card {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long cardId;
    private Long number;
    private String name;
    private Date date;
    @OneToOne
    @JoinColumn(name = "type-currency")
    private TypeCurrency typeOfCurrency;
    private String status;
    private Long balance;

    public Card(Long numberId){
        try{
            this.number = Long.parseLong(numberId+""+(long) Math.floor(Math.random() * 9_000_000_000L));
            System.out.println("number = " + number);
        } catch (Exception ex){
            ex.printStackTrace(System.err);
        }
    }
}
