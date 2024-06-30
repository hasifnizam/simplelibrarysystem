package com.example.simplelibrarysystem.service;

import com.example.simplelibrarysystem.common.BorrowerRequest;
import org.springframework.http.ResponseEntity;

public interface BorrowerService {

    ResponseEntity<?> addBorrower(BorrowerRequest request);

    ResponseEntity<?> getAllBorrower();
}
