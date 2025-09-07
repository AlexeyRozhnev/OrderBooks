package com.example.OrderBooks.controller.payload;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UpdateOrderPayload(
        @NotNull(message = "{library.order.update.errors.book_is_null}")
        Long bookId,
        @NotNull(message = "{library.order.update.errors.client_is_null}")
        Long clientId,
        LocalDate orderDate
) {
}
