package com.example.OrderBooks.service;

import com.example.OrderBooks.entity.Order;

import java.util.Optional;
import java.time.LocalDate;

public interface OrderService {

    Iterable<Order> findAllOrders();

    Order createOrder(Long bookId, Long ClientId, LocalDate orderDate);

    Optional<Order> findOrder(Long id);

    void deleteOrder(Long id);
}
