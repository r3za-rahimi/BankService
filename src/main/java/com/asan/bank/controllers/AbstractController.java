package com.asan.bank.controllers;

import com.asan.bank.services.AbstractService;
import com.asan.bank.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;


public class AbstractController <E, S extends AbstractService<E ,  ? extends JpaRepository<E , Long>>>{


    @Autowired
    protected S service;

    @Autowired
    protected JwtService jwtService;





}
