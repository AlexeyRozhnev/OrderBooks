package com.example.OrderBooks.service;

import com.example.OrderBooks.entity.Book;

import java.util.Optional;

public interface BookService {

    Iterable<Book> findAllBooks();

    Book createBook(String isbn, String title, String author);

    Optional<Book> findBook(Long id);

    void updateBook(Long id, String isbn, String title, String author);

    void deleteBook(Long id);
}
