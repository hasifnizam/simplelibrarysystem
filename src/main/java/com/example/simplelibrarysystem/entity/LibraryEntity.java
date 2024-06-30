package com.example.simplelibrarysystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity(name = "LIBRARY")
public class LibraryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "BORROWER_ID", nullable = false, referencedColumnName = "id")
    @ManyToOne
    private BorrowerEntity borrowerId;

    @JoinColumn(name = "BOOK_ID", nullable = false, referencedColumnName = "id")
    @ManyToOne
    private BookEntity bookId;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "RETURN_AT")
    private LocalDateTime returnAt;
}
