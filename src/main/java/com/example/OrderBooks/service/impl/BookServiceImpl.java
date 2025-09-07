package com.example.OrderBooks.service.impl;

import com.example.OrderBooks.entity.Book;
import com.example.OrderBooks.repository.BookRepository;
import com.example.OrderBooks.service.BookService;

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

    @Override
    public Iterable<Book> findAllBooks() {
        return this.bookRepository.findAll();
    }
    
    @Override
    @Transactional
    public Book createBook(String isbn, String title, String author) {
        return this.bookRepository.save(new Book(null, isbn, title, author));
    }

    @Override
    public Optional<Book> findBook(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    @Transactional
    public void updateBook(Long id, String isbn, String title, String author) {
        this.bookRepository.findById(id)
                .ifPresentOrElse(updatedBook -> {
                    updatedBook.setIsbn(isbn);
                    updatedBook.setTitle(title);
                    updatedBook.setAuthor(author);
                }, () -> {
                    throw new NoSuchElementException();
                });
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        this.bookRepository.deleteById(id);
    }
}