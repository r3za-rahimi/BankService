package com.asan.bank.controllers;

import com.asan.bank.models.Transaction;
import com.asan.bank.models.dto.*;
import com.asan.bank.services.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank")
public class TransactionController extends AbstractController<Transaction, TransactionService> {


    @PostMapping("/payment")
    public ResponseEntity<Response> doPayment(@Valid @RequestBody PaymentRequest request , HttpServletRequest httpRequest) {

        service.payment(request);
        return new ResponseEntity<>(new Response("success transaction"),HttpStatus.ACCEPTED);
    }

    @PostMapping("/card")
    public ResponseEntity<Response> doC2C( @Valid @RequestBody CardRequest request) {

        service.cardToCard(request);
        return new ResponseEntity<>(new Response("success transaction"),HttpStatus.ACCEPTED);
    }

    @PostMapping("/sheba")
    public ResponseEntity<Response> doSheba(  @Valid @RequestBody ShebaPayaRequest request) {

        service.sheba(request);
        return new ResponseEntity<>(new Response("success transaction"),HttpStatus.ACCEPTED);
    }

    @PostMapping("/paya")
    public ResponseEntity<Response> doPaya( @Valid @RequestBody ShebaPayaRequest request) {

        service.paya(request);
        return new ResponseEntity<>(new Response("success transaction"),HttpStatus.ACCEPTED);
    }

    @PostMapping("/account")
    public ResponseEntity<Response> doAccount( @Valid @RequestBody AccountRequest request) {

        service.account(request);
        return new ResponseEntity<>(new Response("success transaction"),HttpStatus.ACCEPTED);

    }

}
