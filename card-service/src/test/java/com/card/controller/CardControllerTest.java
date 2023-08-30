package com.card.controller;


import com.card.CardServiceApplication;
import com.card.entity.Card;
import com.card.entity.TypeCurrency;
import com.card.repository.CardRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@RunWith(SpringRunner.class)
public class CardControllerTest {

    @Autowired
    private CardRepository repository;

    @Test
    public void CardRepository_Save_ReturnSavedCard() {
        Card myCard = Card.builder().cardId(10L)
                .number(123456789L)
                .name("Juanes")
                .date(new Date())
                .typeOfCurrency(new TypeCurrency(1L, "USD"))
                .status("A")
                .balance(0L)
                .build();
        Card cardSaved = repository.save(myCard);
        Assertions.assertThat(cardSaved).isNotNull();
        Assertions.assertThat(cardSaved.getCardId()).isGreaterThan(0L);
    }
}