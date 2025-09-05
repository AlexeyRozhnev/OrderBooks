package com.example.OrderBooks.service.Impl;

import com.example.OrderBooks.entity.Order;
import com.example.OrderBooks.repository.OrderRepository

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;

    @Override
    public Iterable<Order> findAllBooks() {
            return this.orderRepository.findAll();
        }
    }

    @Override
    @Transactional
    public Order createOrder(Long bookId, Long clientId, LocalDate orderDate) {
        return this.orderRepository.save(new Product(bookId, clientId, orderDate));
    }

    @Override
    public Optional<Order> findOrder(Long id) {
        return this.orderRepository.findById(id);
    }

    @Override
    @Transactional
    public void updateOrder(Long bookId, Long clientId, LocalDate orderDate) {
        this.orderRepository.findById(id)
                .ifPresentOrElse(order -> {
					book.setBook(this.bookRepository.findById(bookId));
                    book.setClient(this.clientRepository.findById(clientId));
                    book.setOrderDate(orderDate);
                }, () -> {
                    throw new NoSuchElementException();
                });
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        this.orderRepository.deleteById(id);
    }
}