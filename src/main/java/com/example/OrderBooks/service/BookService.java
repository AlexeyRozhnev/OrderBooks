package com.example.OrderBooks.service;

import com.example.OrderBooks.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Iterable<Book> findAllBooks();

    Book createBook(Book book);

    Optional<Book> findBook(Long id);

    void updateBook(Book book);

    void deleteBook(Long id);
}
