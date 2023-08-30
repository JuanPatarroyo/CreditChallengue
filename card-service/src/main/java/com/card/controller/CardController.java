package com.card.controller;

import com.card.entity.Card;
import com.card.entity.TypeCurrency;
import com.card.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cardCreated.getCardId()).toUri();
        return ResponseEntity.created(uri).body(cardCreated);
    }

    @DeleteMapping("/card/{cardId}")
    public ResponseEntity<String> deactivatedCard(@PathVariable("cardId") Long cardId) {
        Optional<Card> cardToUpdate = repository.findById(cardId);
        if (cardToUpdate.isEmpty()) {
            return new ResponseEntity<>("No se ha encontrado ninguna tarjeta con el id " + cardId, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        cardToUpdate.ifPresent(card -> {
            card.setStatus("N");
            repository.save(card);
        });
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PostMapping("/card/balance")
    public ResponseEntity<String> loadBalance(@RequestBody Card card) {
        Optional<Card> cardToChargeBalance = repository.findById(card.getCardId());
        if (cardToChargeBalance.isEmpty()) {
            return new ResponseEntity<>("No se ha encontrado ninguna tarjeta con el id " + card.getCardId(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        cardToChargeBalance.ifPresent((cardToCharge -> {
            cardToCharge.setBalance(cardToCharge.getBalance() + card.getBalance());
            repository.save(cardToCharge);
        }));
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @GetMapping("/card/balance/{cardId}")
    public ResponseEntity<String> getBalance(@PathVariable("cardId") Long cardId) {
        Optional<Card> card = repository.findById(cardId);
        return card.map(value -> new ResponseEntity<>("El saldo para la tarjeta con id "+cardId+" es de: "+(value.getBalance() == null ? 0 : value.getBalance()), new HttpHeaders(), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>("No se ha encontrado ninguna tarjeta con el id " + cardId, new HttpHeaders(), HttpStatus.BAD_REQUEST));
    }
}
