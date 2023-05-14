package com.asan.bank.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class JwtService {

    @Autowired
    private AuthService authService;

    public String getAllClaimsFromToken(String token) {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String[] chunks = token.split("\\.");

        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));

        return payload;


    }

    public boolean validUser(String token) {

        if (token.equalsIgnoreCase("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhcmVyem9vZWVvIiwiZXhwIjoxNjgzOTY3NjEyLCJpYXQiOjE2ODM5NjQ2MTJ9.wOf0dm7ui0dGZACX_5p0IsnzPEBQ4R2xjTZrdhxMgUkcHOSpb1By08YNwkCGtuJXgvySgQaUXvtV42zCD_RJEg")) {
            return true;


        } else return false;


//        "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhcmVyem9vZWVvIiwiZXhwIjoxNjgzOTY3NjEyLCJpYXQiOjE2ODM5NjQ2MTJ9.wOf0dm7ui0dGZACX_5p0IsnzPEBQ4R2xjTZrdhxMgUkcHOSpb1By08YNwkCGtuJXgvySgQaUXvtV42zCD_RJEg"

    }
    }
