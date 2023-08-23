package com.card.controller;

import com.card.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TransactionController {

    @Autowired
    private TransactionRepository repository;
}
