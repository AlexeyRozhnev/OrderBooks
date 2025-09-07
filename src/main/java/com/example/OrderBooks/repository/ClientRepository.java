package com.example.OrderBooks.repository;

import com.example.OrderBooks.entity.Client;

import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {

}