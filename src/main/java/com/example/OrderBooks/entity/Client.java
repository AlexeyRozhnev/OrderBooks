package com.example.OrderBooks.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Past;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "library", name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "full_name", nullable = false, length = 255)
    @NotBlank
    @Size(min = 3, max = 255)
    private String fullName;

    @Column(name = "birthdate", nullable = false)
    @Past  
    private LocalDate birthDate;
}

