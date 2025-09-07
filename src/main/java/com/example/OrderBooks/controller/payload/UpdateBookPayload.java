package com.example.OrderBooks.controller.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateBookPayload(
        @NotNull(message = "{library.book.update.errors.isbn_is_null}")
        @Size(max = 20, message = "{library.book.update.errors.isbn_size_is_invalid}")
        String isbn,
        @NotNull(message = "{library.book.update.errors.title_is_null}")
        @Size(min = 3, max = 255, message = "{library.book.update.errors.title_size_is_invalid}")
        String title,
        @NotNull(message = "{library.book.update.errors.author_is_null}")
        @Size(min = 3, max = 255, message = "{library.book.update.errors.author_size_is_invalid}")
        String author
) {
}
