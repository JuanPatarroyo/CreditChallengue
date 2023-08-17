package com.card.controller;

import com.card.entity.Card;
import com.card.entity.TypeCurrency;
import com.card.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;

@Controller
public class CardController {

    @Autowired
    private CardRepository repository;

    @GetMapping("/card/{productId}/number")
    public Long newNumberCard(@PathVariable("productId") Long productId){
        Card card1 = new Card(productId);
        System.out.println("card1 = " + card1);
        return card1.getNumber();
    }
}
