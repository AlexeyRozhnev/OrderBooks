package com.example.OrderBooks.service;

import com.example.OrderBooks.entity.Book;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

public interface OrderService {

    Iterable<Order> findAllOrders();

    Order createOrder(Long bookId, Long ClientId, LocalDate orderDate);

    Optional<Order> findOrder(Long id);

    void updateOrder(Long id, Long bookId, Long clientId, LocalDate orderDate);

    void deleteOrder(Long id);
}
