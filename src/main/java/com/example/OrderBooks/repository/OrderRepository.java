package com.example.OrderBooks.repository;

import com.example.OrderBooks.entity.Order;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

}