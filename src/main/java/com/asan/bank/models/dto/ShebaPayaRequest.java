package com.asan.bank.models.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ShebaPayaRequest {

    @Pattern(regexp = "\\d{24}" , message = "Sheba Number Must Have 24 Digit")
    private String Sheba;

    private Long amount;

}
