package com.asan.bank.services;

import com.asan.bank.models.DealType;
import com.asan.bank.models.Transaction;
import com.asan.bank.models.TransactionType;
import com.asan.bank.models.dto.AccountRequest;
import com.asan.bank.models.dto.CardRequest;
import com.asan.bank.models.dto.PaymentRequest;
import com.asan.bank.models.dto.ShebaPayaRequest;
import com.asan.bank.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService extends AbstractService<Transaction, TransactionRepository> {


    public void payment(PaymentRequest request){

        Transaction transaction = Transaction.builder().
                transactionType(TransactionType.PAYMENT).
                dealType(DealType.WITHDRAW)
                .amount(request.getAmount()).build();

        repository.save(transaction);

    }

    public void cardToCard(CardRequest request){

        Transaction transaction = Transaction.builder().
                transactionType(TransactionType.C2C).
                dealType(DealType.WITHDRAW)
                .amount(request.getAmount()).build();

        repository.save(transaction);

    }

    public void sheba(ShebaPayaRequest request){

        Transaction transaction = Transaction.builder().
                transactionType(TransactionType.SHEBA).
                dealType(DealType.WITHDRAW)
                .amount(request.getAmount()).build();

        repository.save(transaction);

    }

    public void paya(ShebaPayaRequest request){

        Transaction transaction = Transaction.builder().
                transactionType(TransactionType.PAYA).
                dealType(DealType.WITHDRAW)
                .amount(request.getAmount()).build();

        repository.save(transaction);

    }

    public void account(AccountRequest request){

        Transaction transaction = Transaction.builder().
                transactionType(TransactionType.A2A).
                dealType(DealType.WITHDRAW)
                .amount(request.getAmount()).build();

        repository.save(transaction);

    }


}
