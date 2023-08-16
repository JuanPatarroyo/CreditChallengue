package com.card.controller;

import com.card.entity.Card;
import com.card.entity.TypeCurrency;
import com.card.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Controller
public class CardController {

    @Autowired
    private CardRepository repository;

    @GetMapping("/prueba")
    public String prueba(){
        Card card1 = new Card("Juan Patarroyo", new Date(), new TypeCurrency(1L, "USD"));
        System.out.println("card1 = " + card1);
        repository.save(card1);
        return "Hola";
    }
}
