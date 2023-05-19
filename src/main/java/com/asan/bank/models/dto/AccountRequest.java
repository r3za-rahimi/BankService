package com.asan.bank.models.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
public class AccountRequest  {

    @Pattern(regexp = "\\d{10}" , message = "Source Account Number Must Have 10 Digit")
    private String sourceAccountNumber;

    @Pattern(regexp = "\\d{10}" , message = " Destination Account Number Must Have 10 Digit")
    private String destinationAccountNumber;

    private Long amount;

}
