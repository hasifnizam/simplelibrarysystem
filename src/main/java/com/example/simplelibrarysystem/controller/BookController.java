package com.example.simplelibrarysystem.controller;

import com.example.simplelibrarysystem.common.BookRequest;
import com.example.simplelibrarysystem.common.CommonResponse;
import com.example.simplelibrarysystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping(value = "/add")
    ResponseEntity<?> addBook(@RequestBody BookRequest request) {
        return bookService.addBook(request);
    }

    @GetMapping(value = "/getAll")
    ResponseEntity<?> getAllBook() {
        return bookService.getAllBook();
    }
}
