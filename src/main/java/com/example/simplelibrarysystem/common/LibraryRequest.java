package com.example.simplelibrarysystem.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LibraryRequest {
    private int bookId;
    private String borrowerEmail;
    private Action action;
}
