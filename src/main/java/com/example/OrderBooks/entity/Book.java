package com.example.OrderBooks.entity;

import jakarta.persistence.*;
import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "library", name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "isbn", nullable = false, unique = true, length = 20)
    @NotBlank
    private String isbn;
	
	@Column(name = "title", nullable = false, length = 255)
	@NotBlank
	@Size(min = 3, max = 255)
    private String title;
	
    @Column(name = "author", nullable = false, length = 255)
    @NotBlank
    @Size(min = 3, max = 255)
    private String author;

    
}

