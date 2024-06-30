package com.example.simplelibrarysystem.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookRequest {
    private String isbnNo;
    private String title;
    private String author;
}
