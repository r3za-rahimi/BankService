package com.asan.bank;

import com.asan.bank.models.dto.AccountRequest;
import com.asan.bank.models.dto.CardRequest;
import com.asan.bank.models.dto.PaymentRequest;
import com.asan.bank.models.dto.ShebaPayaRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BankApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;


    private String jwtToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhcmVyem9vZWVvIiwiZXhwIjoxNjgzOTY3NjEyLCJpYXQiOjE2ODM5NjQ2MTJ9.wOf0dm7ui0dGZACX_5p0IsnzPEBQ4R2xjTZrdhxMgUkcHOSpb1By08YNwkCGtuJXgvySgQaUXvtV42zCD_RJEg";

    @Test
    void contextLoads() {
    }


    @Test
    public void testHappyPayment() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                        .post("/bank/payment").header("jwt", jwtToken)
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(PaymentRequest.builder()
                                .amount(500L).
                                cardNumber("6104337722156021").
                                otp(5650).
                                cvv2("478").
                                expireTime("05-04")
                                .build())))
                .andExpect(status().isAccepted());
        ;


    }

    @Test
    public void testExceptionPayment() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                        .post("/bank/payment").header("jwt", jwtToken)
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(PaymentRequest.builder()
                                .amount(500L).
                                cardNumber("610422156021").
                                otp(5650).
                                cvv2("478").
                                expireTime("05-04")
                                .build())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value(true))
                .andExpect(jsonPath("$.message").value("Card Number Must Have 16 Digit"));


    }

    @Test
    public void testExceptionPaymentForAuthorization() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                        .post("/bank/payment").header("jwt", 555)
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(PaymentRequest.builder()
                                .amount(500L).
                                cardNumber("6104337722156021").
                                otp(5650).
                                cvv2("478").
                                expireTime("05-04")
                                .build())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Authorization_Failed_Login_Again"));


    }


    @Test
    public void testHappyCard() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                        .post("/bank/card").header("jwt", jwtToken)
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(CardRequest.builder()
                                .amount(500L).
                                DestinationCardNumber("6104337722156021")
                                .sourceCardNumber("6104337722156022").
                                otp(5650).
                                cvv2(555).
                                expireTime("05-04")
                                .build())))
                .andExpect(status().isAccepted());
        ;


    }

    @Test
    public void testExceptionCard() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                        .post("/bank/card").header("jwt", jwtToken)
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(CardRequest.builder()
                                .amount(500L).
                                DestinationCardNumber("610433772215602")
                                .sourceCardNumber("6104337722156022").
                                otp(5650).
                                cvv2(555).
                                expireTime("05-04")
                                .build())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value(true))
                .andExpect(jsonPath("$.message").value("Destination Card Number Must Have 16 Digit"));


    }

    @Test
    public void testExceptionCardAuthorization() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                        .post("/bank/card").header("jwt", 5555)
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(CardRequest.builder()
                                .amount(500L).
                                DestinationCardNumber("6104337722156021")
                                .sourceCardNumber("6104337722156022").
                                otp(5650).
                                cvv2(555).
                                expireTime("05-04")
                                .build())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Authorization_Failed_Login_Again"));


    }


    @Test
    public void testHappyAccount() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                        .post("/bank/account").header("jwt", jwtToken)
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(AccountRequest.builder()
                                .amount(500L)
                                .DestinationAccountNumber("1234567891")
                                .sourceAccountNumber("1234567891")
                                .build())))
                .andExpect(status().isAccepted());
        ;


    }

    @Test
    public void testExceptionAccount() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                        .post("/bank/account").header("jwt", jwtToken)
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(AccountRequest.builder()
                                .amount(500L)
                                .DestinationAccountNumber("1234567891")
                                .sourceAccountNumber("123457891")
                                .build())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value(true))
                .andExpect(jsonPath("$.message").value("Source Account Number Must Have 10 Digit"));


    }

    @Test
    public void testExceptionAccountAuthorization() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                        .post("/bank/account").header("jwt", 5555)
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(AccountRequest.builder()
                                .amount(500L)
                                .DestinationAccountNumber("1234567891")
                                .sourceAccountNumber("1234567891")
                                .build())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Authorization_Failed_Login_Again"));


    }


    @Test
    public void testHappySheba() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                        .post("/bank/sheba").header("jwt", jwtToken)
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(ShebaPayaRequest.builder()
                                .amount(500L)
                                .Sheba("123456789123456789123456")
                                .build())))
                .andExpect(status().isAccepted());
        ;


    }

    @Test
    public void testExceptionSheba() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                        .post("/bank/sheba").header("jwt", jwtToken)
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(ShebaPayaRequest.builder()
                                .amount(500L)
                                .Sheba("1234567891236789123456")
                                .build())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value(true))
                .andExpect(jsonPath("$.message").value("Sheba Number Must Have 24 Digit"));


    }

    @Test
    public void testExceptionShebaAuthorization() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                        .post("/bank/sheba").header("jwt", 555)
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(ShebaPayaRequest.builder()
                                .amount(500L)
                                .Sheba("123456789123456789123456")
                                .build())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Authorization_Failed_Login_Again"));


    }


    @Test
    public void testHappyPaya() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                        .post("/bank/paya").header("jwt", jwtToken)
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(ShebaPayaRequest.builder()
                                .amount(500L)
                                .Sheba("123456789123456789123456")
                                .build())))
                .andExpect(status().isAccepted());
        ;


    }

    @Test
    public void testExceptionPaya() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                        .post("/bank/paya").header("jwt", jwtToken)
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(ShebaPayaRequest.builder()
                                .amount(500L)
                                .Sheba("123456789123678923456")
                                .build())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value(true))
                .andExpect(jsonPath("$.message").value("Sheba Number Must Have 24 Digit"));


    }


    @Test
    public void testExceptionPayaAuthorization() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                        .post("/bank/paya").header("jwt", 555)
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(ShebaPayaRequest.builder()
                                .amount(500L)
                                .Sheba("123456789123456789123456")
                                .build())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Authorization_Failed_Login_Again"));


    }


}
