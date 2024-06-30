package com.example.simplelibrarysystem.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BorrowerRequest {
    private String name;
    private int age;
    private String email;
}
