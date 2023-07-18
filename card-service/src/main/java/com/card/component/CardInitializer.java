package com.card.component;

import com.card.entity.Card;
import com.card.entity.TypeCurrency;
import com.card.repository.CardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CardInitializer implements CommandLineRunner {
    private final CardRepository repository;

    CardInitializer(CardRepository repository){
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        Card card1 = new Card("Juan Patarroyo", new Date(), new TypeCurrency(1L, "USD"));
        System.out.println("card1 = " + card1);
    }
}
