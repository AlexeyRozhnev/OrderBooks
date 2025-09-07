package com.example.OrderBooks.repository;

import com.example.OrderBooks.entity.Book;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {

}