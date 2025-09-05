package com.example.management_system.controller;

import com.example.management_system.model.Order;
import com.example.management_system.service.OrderService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return this.orderService .findAll();
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
        return this.orderService .findById(id);
    }

    @PostMapping
    public Order createOrder(@RequestParam Long clientId, @RequestParam Long bookId) {
        return this.orderService .createBorrow(clientId, bookId);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        this.orderService.deleteById(id);
    }
}
