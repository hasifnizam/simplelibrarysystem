package com.example.simplelibrarysystem.service;

import com.example.simplelibrarysystem.common.LibraryRequest;
import org.springframework.http.ResponseEntity;

public interface LibraryService {

    ResponseEntity<?> processBook(LibraryRequest request);
    ResponseEntity<?> getAllList();
}
