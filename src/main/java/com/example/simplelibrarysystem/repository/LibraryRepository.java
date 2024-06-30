package com.example.simplelibrarysystem.repository;

import com.example.simplelibrarysystem.entity.BookEntity;
import com.example.simplelibrarysystem.entity.BorrowerEntity;
import com.example.simplelibrarysystem.entity.LibraryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibraryRepository extends JpaRepository<LibraryEntity, String> {

    List<LibraryEntity> findByBorrowerIdAndBookIdAndReturnAtNull(BorrowerEntity borrower, BookEntity book);
    List<LibraryEntity> findByBookIdAndReturnAtNull(BookEntity book);
}
