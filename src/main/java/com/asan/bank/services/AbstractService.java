package com.asan.bank.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AbstractService<E , R extends JpaRepository<E, Long>> {

    @Autowired
    protected R repository;


    public E insert(E e) {

        return repository.save(e);

    }




}
