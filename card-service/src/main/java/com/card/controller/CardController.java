package com.card.controller;

import com.card.entity.Card;
import com.card.entity.TypeCurrency;
import com.card.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.Optional;

@Controller
public class CardController {

    @Autowired
    private CardRepository repository;

    @GetMapping("/card/{productId}/number")
    public ResponseEntity<Long> generateCardNumber(@PathVariable("productId") Long productId) {
        Card card1 = new Card(productId);
        return new ResponseEntity<>(card1.getNumber(), HttpStatus.OK);
    }

    @PostMapping("/card/enroll")
    public ResponseEntity<Card> enrollCreditCard(@RequestBody Card card) {
        Card cardCreated = repository.save(card);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cardCreated.getId()).toUri();
        return ResponseEntity.created(uri).body(cardCreated);
    }

    @DeleteMapping("/card/{cardId}")
    public ResponseEntity<String> deactivatedCard(@PathVariable("cardId") Long cardId){
        Optional<Card> cardToUpdate = repository.findById(cardId);
        cardToUpdate.ifPresent(card -> {
            card.setStatus("N");
            repository.save(card);
        });
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
