package com.example.OrderBooks.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "library", name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne(optional = false)
    @JoinColumn(
		name = "client_id", 
		nullable = false, 
		foreignKey = @ForeignKey(name = "fk_borrow_client")
	)
    @NotNull
    private Client client;

    @ManyToOne(optional = false)
    @JoinColumn(
		name = "book_id", 
		nullable = false,
        foreignKey = @ForeignKey(name = "fk_order_book")
	)
    @NotNull
    private Book book;

    @Column(name = "order_date", nullable = false)
    @NotNull
    private LocalDate orderDate = LocalDate.now();
}

