package com.example.OrderBooks.controller;

import com.example.OrderBooks.controller.payload.NewClientPayload;
import com.example.OrderBooks.entity.Client;
import com.example.OrderBooks.entity.Order;
import com.example.OrderBooks.service.OrderService;
import com.example.OrderBooks.controller.payload.NewOrderPayload;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import jakarta.validation.Valid;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;
    private final MessageSource messageSource;

    public OrderController(OrderService orderService, MessageSource messageSource) {
        this.orderService = orderService;
        this.messageSource = messageSource;
    }

    @GetMapping
    public Iterable<Order> getAllOrders() {
        return orderService.findAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
        return orderService.findOrder(id).orElseThrow(() -> new NoSuchElementException("{library.errors.order.not_found}"));
    }

    @PostMapping
    public ResponseEntity<?> createClient(@Valid @RequestBody NewOrderPayload payload,
                                          BindingResult bindingResult,
                                          UriComponentsBuilder uriComponentsBuilder)
            throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            Order order = orderService.createOrder(payload.clientId(), payload.bookId(), payload.orderDate());
            return ResponseEntity
                    .created(uriComponentsBuilder
                            .replacePath("/api/orders/{id}")
                            .build(Map.of("id", order.getId())))
                    .body(order);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent()
                .build();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ProblemDetail> handleNoSuchElementException(NoSuchElementException exception,
                                                                      Locale locale) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                        messageSource.getMessage(exception.getMessage(), new Object[0],
                                exception.getMessage(), locale)));
    }
}
