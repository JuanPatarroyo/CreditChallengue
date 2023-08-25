package com.card.controller;

import com.card.dto.TransactionDto;
import com.card.entity.Card;
import com.card.entity.Transaction;
import com.card.repository.CardRepository;
import com.card.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.Optional;

@Controller
public class TransactionController {

    @Autowired
    private TransactionRepository repository;
    @Autowired
    private CardRepository repositoryCard;

    @PostMapping("/transaction/purchase")
    public ResponseEntity<Transaction> purchase(@RequestBody TransactionDto transaction) {
        Optional<Card> card = repositoryCard.findById(transaction.getCardId());
        if (card.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Transaction transactionToCreate = new Transaction(0L, card.get(), new Date(), transaction.getPrice());
        transactionToCreate = repository.save(transactionToCreate);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(transactionToCreate.getTransactionId()).toUri();
        return ResponseEntity.created(uri).body(transactionToCreate);
    }

    @GetMapping("/transaction/{transactionId}")
    public ResponseEntity<Transaction> findTransactionById(@PathVariable("transactionId") Long transactionId){
        Optional<Transaction> transaction = repository.findById(transactionId);
        System.out.println("transaction.get() = " + transaction.get());
        return transaction.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
