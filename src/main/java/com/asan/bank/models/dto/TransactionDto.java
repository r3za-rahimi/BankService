package com.asan.bank.models.dto;

import com.asan.bank.models.DealType;

public class TransactionDto extends AbstractDto{


    private Long amount;
    private String trackingId;
    private DealType dealType;
}
