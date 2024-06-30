package com.example.simplelibrarysystem.service.impl;

import com.example.simplelibrarysystem.common.BorrowerRequest;
import com.example.simplelibrarysystem.common.CommonResponse;
import com.example.simplelibrarysystem.common.LibraryConstant;
import com.example.simplelibrarysystem.entity.BorrowerEntity;
import com.example.simplelibrarysystem.repository.BorrowerRepository;
import com.example.simplelibrarysystem.service.BorrowerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Slf4j
public class BorrowerServiceImpl implements BorrowerService {

    @Autowired
    private BorrowerRepository borrowerRepository;

    @Override
    public ResponseEntity<?> addBorrower(BorrowerRequest request) {
        CommonResponse response = new CommonResponse();
        log.info("Initiate add borrower...");

        //Check user already exist or not based on the email
        if (checkUserExist(request.getEmail())) {
            log.info("Borrower doesn't exists in DB...");

            //If not exist, add into db
            boolean result = addNewBorrower(request);

            if(result) {
                response.setMessage(LibraryConstant.BORROWER_SUCCESS);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            response.setMessage(LibraryConstant.BORROWER_FAILED);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("Borrower already exists...");
        response.setMessage(LibraryConstant.BORROWER_EXISTS);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> getAllBorrower() {
        log.info("Get all borrower list...");
        List<BorrowerEntity> borrowerEntityList = borrowerRepository.findAll();

        return new ResponseEntity<>(borrowerEntityList, HttpStatus.OK);
    }

    private boolean checkUserExist(String email) {
        log.info("Check user {} existence...", email);
        List<BorrowerEntity> borrower = borrowerRepository.findByEmail(email);

        return CollectionUtils.isEmpty(borrower);
    }

    private boolean addNewBorrower(BorrowerRequest request) {
        BorrowerEntity borrower = new BorrowerEntity();
        borrower.setName(request.getName());
        borrower.setAge(request.getAge());
        borrower.setEmail(request.getEmail());

        try{
            borrowerRepository.save(borrower);
            return true;
        } catch (Exception e) {
            log.error("Error while add new borrower...\nError: {}", e.getMessage());
            return false;
        }
    }
}
