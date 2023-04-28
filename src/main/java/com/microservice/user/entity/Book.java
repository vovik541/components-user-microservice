package com.microservice.user.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;


@Entity
@Table(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name="user_id")
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "book_name", nullable = false, unique = true, length = 120)
    private String bookName;

    @Column(nullable = false, length = 64)
    private String author;

    @Column(nullable = false, length = 1200)
    private String description;

    @Column(name = "is_booked", nullable=false, length = 20)
    private boolean isBooked;
}
