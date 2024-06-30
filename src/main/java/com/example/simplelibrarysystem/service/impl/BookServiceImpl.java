package com.example.simplelibrarysystem.service.impl;

import com.example.simplelibrarysystem.common.BookRequest;
import com.example.simplelibrarysystem.common.CommonResponse;
import com.example.simplelibrarysystem.common.LibraryConstant;
import com.example.simplelibrarysystem.entity.BookEntity;
import com.example.simplelibrarysystem.repository.BookRepository;
import com.example.simplelibrarysystem.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public ResponseEntity<?> addBook(BookRequest request) {
        CommonResponse response = new CommonResponse();
        log.info("Adding new book...");

        //Map request to book entity
        BookEntity bookEntity = mapToBook(request);

        //Check book already exist or not
        if(validateBook(bookEntity)){
            try{
                bookRepository.save(bookEntity);

                response.setMessage(LibraryConstant.BOOK_SUCCESS);
                log.info("Successfully add new book...");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Error while add new book!\nError: {}", e.getMessage());
                response.setMessage(LibraryConstant.BOOK_FAILED);
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        response.setMessage(LibraryConstant.BOOK_NOT_MATCH_CRITERIA);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> getAllBook() {
        log.info("Getting all the book available in the library...");
        List<BookEntity> bookEntityList = bookRepository.findAll();

        return new ResponseEntity<>(bookEntityList, HttpStatus.OK);
    }

    private BookEntity mapToBook(BookRequest request) {
        BookEntity book = new BookEntity();
        book.setIsbnNo(request.getIsbnNo());
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());

        return book;
    }

    private boolean validateBook(BookEntity bookEntity) {
        log.info("Proceed with book validation!...");
        List<BookEntity> bookEntityList = bookRepository.getByIsbnNo(bookEntity.getIsbnNo());

        //If list of book is not empty
        if(!CollectionUtils.isEmpty(bookEntityList)) {
            log.info("Existing ISBN found! proceed with next checking...");
            if(bookEntityList.get(0).getTitle().equalsIgnoreCase(bookEntity.getTitle()) &&
                    bookEntityList.get(0).getAuthor().equalsIgnoreCase(bookEntity.getAuthor())) {
                log.info("Title and Author match the rule. proceed with adding duplicate book...");
                return true;
            }
            log.info("Title and Author doesn't match the rule. returning...");
            return false;
        }
        log.info("ISBN is not found. proceed to create new book...");
        return true;
    }
}
