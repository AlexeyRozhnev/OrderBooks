package com.example.OrderBooks.controller;

import com.example.OrderBooks.controller.payload.NewBookPayload;
import com.example.OrderBooks.controller.payload.UpdateBookPayload;
import com.example.OrderBooks.entity.Book;
import com.example.OrderBooks.service.BookService;
import jakarta.validation.Valid;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    private final MessageSource messageSource;

    public BookController(BookService bookService, MessageSource messageSource) {
        this.bookService = bookService;
        this.messageSource = messageSource;
    }

    @GetMapping
    public Iterable<Book> getAllBooks() {
        return bookService.findAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        return bookService.findBook(id).orElseThrow(() -> new NoSuchElementException("{library.errors.book.not_found}"));
    }

    @PostMapping
    public ResponseEntity<?> createBook(@Valid @RequestBody NewBookPayload payload,
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
            Book book = bookService.createBook(payload.isbn(), payload.title(), payload.author());
            return ResponseEntity
                    .created(uriComponentsBuilder
                            .replacePath("/api/books/{id}")
                            .build(Map.of("id", book.getId())))
                    .body(book);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable("id") Long id,
                                           @Valid @RequestBody UpdateBookPayload payload,
                                           BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            bookService.updateBook(id, payload.isbn(), payload.title(), payload.author());
            return ResponseEntity.noContent()
                    .build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
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
