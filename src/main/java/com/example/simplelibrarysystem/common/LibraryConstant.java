package com.example.simplelibrarysystem.common;

public class LibraryConstant {

    public static final String BORROWER_SUCCESS = "Successfully add new borrower details";
    public static final String BORROWER_EXISTS = "Failed to add new borrower. Email already exists!";
    public static final String BORROWER_FAILED = "Failed to add new borrower. Please contact support...";
    public static final String BOOK_FAILED = "Failed to add new book. Please contact support...";
    public static final String BOOK_SUCCESS = "Successfully add new book";
    public static final String BOOK_NOT_MATCH_CRITERIA = "Failed to add new book. ISBN already exists. Title and Author must be the same as previous details!";
    public static final String RECORD_SUCCESS = "Successfully add new record";
    public static final String RECORD_RETURN_SUCCESS = "Successfully returning book";
    public static final String RECORD_FAILED = "Failed to add new record. Please contact support...";
    public static final String RECORD_RETURN_FAILED = "Failed to return the book. Please contact support...";
    public static final String RECORD_EXISTS = "Failed to add new record. Record already exists or book is not available at the moment!";
    public static final String RECORD_RETURN_NOT_EXISTS = "Failed to return the book. Record doesn't exists!";
    public static final String RECORD_BORROWER_BOOK_NOT_VALID = "Failed to add new record. Borrower or book invalid!";
    public static final String RECORD_RETURN_BORROWER_BOOK_NOT_VALID = "Failed to return the book. Borrower or book invalid!";
}
