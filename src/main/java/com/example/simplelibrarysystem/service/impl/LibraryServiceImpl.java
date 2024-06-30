package com.example.simplelibrarysystem.service.impl;

import com.example.simplelibrarysystem.common.Action;
import com.example.simplelibrarysystem.common.CommonResponse;
import com.example.simplelibrarysystem.common.LibraryConstant;
import com.example.simplelibrarysystem.common.LibraryRequest;
import com.example.simplelibrarysystem.entity.BookEntity;
import com.example.simplelibrarysystem.entity.BorrowerEntity;
import com.example.simplelibrarysystem.entity.LibraryEntity;
import com.example.simplelibrarysystem.repository.BookRepository;
import com.example.simplelibrarysystem.repository.BorrowerRepository;
import com.example.simplelibrarysystem.repository.LibraryRepository;
import com.example.simplelibrarysystem.service.LibraryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private BorrowerRepository borrowerRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private LibraryRepository libraryRepository;

    @Override
    public ResponseEntity<?> processBook(LibraryRequest request) {
        if(Action.BORROW.equals(request.getAction()))
            return initiateBorrowingProcess(request);
        return initiateReturningProcess(request);
    }

    @Override
     public ResponseEntity<?> getAllList() {
        log.info("Getting all library borrower details...");

        try{
            List<LibraryEntity> libraryEntityList = libraryRepository.findAll();
            return new ResponseEntity<>(libraryEntityList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while getting list from DB.\nError: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<?> initiateBorrowingProcess(LibraryRequest request) {
        CommonResponse response = new CommonResponse();
        log.info("Initiate borrowing book...");

        //Get borrower and book details from DB
        BorrowerEntity borrowerEntity = validateBorrower(request.getBorrowerEmail());
        BookEntity bookEntity = validateBook(request.getBookId());

        //If borrower and book is not null
        if(null != borrowerEntity && null != bookEntity) {
            //Check availability of the book
            if(validateAvailability(bookEntity)) {
                log.info("Valid Borrower. Book is exists and available...");
                try {
                    //Map details into library entity
                    LibraryEntity library = mapLibraryDetails(borrowerEntity, bookEntity);
                    libraryRepository.save(library);

                    log.info("Successfully add new record...");
                    response.setMessage(LibraryConstant.RECORD_SUCCESS);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                } catch (Exception e) {
                    log.error("Error while borrow book.\nError: {}", e.getMessage());
                    response.setMessage(LibraryConstant.RECORD_FAILED);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            }
            response.setMessage(LibraryConstant.RECORD_EXISTS);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setMessage(LibraryConstant.RECORD_BORROWER_BOOK_NOT_VALID);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<?> initiateReturningProcess(LibraryRequest request) {
        CommonResponse response = new CommonResponse();
        log.info("Initiate returning book...");

        //Get borrower and book details from DB
        BorrowerEntity borrowerEntity = validateBorrower(request.getBorrowerEmail());
        BookEntity bookEntity = validateBook(request.getBookId());

        //If borrower and book is not null
        if(null != borrowerEntity && null != bookEntity) {
            log.info("Borrower and Book is valid...");
            //Get book with return date null
            LibraryEntity library = validateExits(borrowerEntity, bookEntity);

            if(null != library) {
                log.info("Record found in the database");
                try {
                    LocalDateTime nowDateTime = LocalDateTime.now();

                    library.setReturnAt(nowDateTime);
                    libraryRepository.save(library);

                    log.info("Successfully return the book...");
                    response.setMessage(LibraryConstant.RECORD_RETURN_SUCCESS);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                } catch (Exception e) {
                    log.error("Error while returning book.\nError: {}", e.getMessage());
                    response.setMessage(LibraryConstant.RECORD_RETURN_FAILED);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            }
            response.setMessage(LibraryConstant.RECORD_RETURN_NOT_EXISTS);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setMessage(LibraryConstant.RECORD_RETURN_BORROWER_BOOK_NOT_VALID);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private BorrowerEntity validateBorrower(String borrowerEmail) {
        log.info("Proceed with validating borrower details...");
        List<BorrowerEntity> borrowerEntityList = borrowerRepository.findByEmail(borrowerEmail);

        if(!CollectionUtils.isEmpty(borrowerEntityList))
            return borrowerEntityList.get(0);
        return null;
    }

    private BookEntity validateBook(int bookId) {
        log.info("Proceed with validating the book request...");
        List<BookEntity> bookEntityList = bookRepository.getById(bookId);

        if(!CollectionUtils.isEmpty(bookEntityList))
            return bookEntityList.get(0);
        return null;
    }

    private boolean validateAvailability(BookEntity book) {
        log.info("Proceed with checking the borrowing list...");
        List<LibraryEntity> libraryEntityList = libraryRepository.findByBookIdAndReturnAtNull(book);

        if(CollectionUtils.isEmpty(libraryEntityList))
            return true;
        return false;
    }

    private LibraryEntity validateExits(BorrowerEntity borrower, BookEntity book) {
        log.info("Proceed with checking the borrowing list...");
        List<LibraryEntity> libraryEntityList = libraryRepository.findByBorrowerIdAndBookIdAndReturnAtNull(borrower, book);

        if(!CollectionUtils.isEmpty(libraryEntityList))
            return libraryEntityList.get(0);
        return null;
    }

    private LibraryEntity mapLibraryDetails(BorrowerEntity borrower, BookEntity book) {
        LocalDateTime nowDateTime = LocalDateTime.now();

        LibraryEntity library = new LibraryEntity();
        library.setBorrowerId(borrower);
        library.setBookId(book);
        library.setCreatedAt(nowDateTime);

        return library;
    }
}
