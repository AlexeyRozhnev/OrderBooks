package com.example.OrderBooks.controller.payload;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record NewOrderPayload(
        @NotNull(message = "{library.order.create.errors.book_is_null}")
        Long bookId,
		@NotNull(message = "{library.order.create.errors.client_is_null}")
		Long clientId,
		LocalDate orderDate
		) {
}