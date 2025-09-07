package com.example.OrderBooks.service.impl;

import com.example.OrderBooks.entity.Book;
import com.example.OrderBooks.entity.Client;
import com.example.OrderBooks.entity.Order;
import com.example.OrderBooks.repository.BookRepository;
import com.example.OrderBooks.repository.ClientRepository;
import com.example.OrderBooks.repository.OrderRepository;
import com.example.OrderBooks.service.OrderService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final BookRepository bookRepository;
    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;

    @Override
    public Iterable<Order> findAllOrders() {
        return this.orderRepository.findAll();
    }

    @Override
    @Transactional
    public Order createOrder(Long bookId, Long clientId, LocalDate orderDate) {
        Book book = this.bookRepository.findById(bookId).orElseThrow(()-> new NoSuchElementException("library.errors.book.not_found"));
        Client client = this.clientRepository.findById(clientId).orElseThrow(()-> new NoSuchElementException("library.errors.client.not_found"));
        Order order = new Order();
        order.setBook(book);
        order.setClient(client);
        order.setOrderDate(orderDate);
        return this.orderRepository.save(order);
    }

    @Override
    public Optional<Order> findOrder(Long id) {
        return this.orderRepository.findById(id);
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        this.orderRepository.deleteById(id);
    }
}