package com.example.simplelibrarysystem.controller;

import com.example.simplelibrarysystem.common.LibraryRequest;
import com.example.simplelibrarysystem.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @PostMapping(value = "/action")
    ResponseEntity<?> borrowBook(@RequestBody LibraryRequest request) {
        return libraryService.processBook(request);
    }

    @GetMapping(value = "/getAll")
    ResponseEntity<?> getAllList() {
        return libraryService.getAllList();
    }
}
