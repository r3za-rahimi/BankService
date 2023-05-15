package com.asan.bank.security;


import com.asan.bank.models.dto.AuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "authentication",url = "${url.auth}")
public interface AuthService {

    @GetMapping(value = "/api/token/is-valid")
    AuthResponse isTokenValid(@RequestHeader("Authorization") String token);



}
