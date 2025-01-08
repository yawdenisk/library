package com.example.library.Entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class AuthorResponce {
    private String name;
    private String surname;
    private List<BookResponce> books = new ArrayList<>();
    private Library library;
}
