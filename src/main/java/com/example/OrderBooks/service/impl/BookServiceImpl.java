package com.example.OrderBooks.service.Impl;

import com.example.OrderBooks.entity.Book;
import com.example.OrderBooks.repository.BookRepository

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
    }

    @Override
    @Transactional
    public Book createBook(Book book) {
        return this.bookRepository.save(new Book(book));
    }

    @Override
    public Optional<Book> findBook(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    @Transactional
    public void updateBook(Long id, Book book) {
        this.bookRepository.findById(id)
                .ifPresentOrElse(newBook -> {
					newBook.setIsbn(book.getIsbn);
                    newBook.setTitle(book.getTitle);
                    newBook.setAuthor(book.getAuthor);
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