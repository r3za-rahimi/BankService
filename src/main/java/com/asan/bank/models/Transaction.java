package com.asan.bank.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction extends AbstractEntity{

    private Long amount;

    @Enumerated(EnumType.STRING)
    private DealType dealType;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
}
