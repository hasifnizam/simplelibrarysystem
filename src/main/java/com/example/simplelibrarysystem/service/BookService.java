package com.example.simplelibrarysystem.service;

import com.example.simplelibrarysystem.common.BookRequest;
import com.example.simplelibrarysystem.common.CommonResponse;
import org.springframework.http.ResponseEntity;

public interface BookService {

    ResponseEntity<?> addBook(BookRequest request);
    ResponseEntity<?> getAllBook();
}
