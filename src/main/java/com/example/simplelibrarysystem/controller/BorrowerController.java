package com.example.simplelibrarysystem.controller;

import com.example.simplelibrarysystem.common.BorrowerRequest;
import com.example.simplelibrarysystem.service.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/borrower")
public class BorrowerController {

    @Autowired
    private BorrowerService borrowerService;

    @PostMapping(value = "/add")
    ResponseEntity<?> addBorrower(@RequestBody BorrowerRequest request) {
        return borrowerService.addBorrower(request);
    }

    @GetMapping(value = "/getAll")
    ResponseEntity<?> getAllBorrower() {
        return borrowerService.getAllBorrower();
    }
}
