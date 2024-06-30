package com.example.simplelibrarysystem.repository;

import com.example.simplelibrarysystem.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, String> {

    List<BookEntity> getByIsbnNo(String isbnNo);
    List<BookEntity> getById(int id);
}
