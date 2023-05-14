package com.asan.bank.models.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AccountRequest  {

    @Pattern(regexp = "\\d{10}" , message = "Source Account Number Must Have 10 Digit")
    private String sourceAccountNumber;

    @Pattern(regexp = "\\d{10}" , message = " Destination Account Number Must Have 10 Digit")
    private String DestinationAccountNumber;

    private Long amount;

}
