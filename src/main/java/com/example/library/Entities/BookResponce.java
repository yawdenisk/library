package com.example.library.Entities;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class BookResponce {
    private String title;
    private Author author;
    private String description;
    private Float price;
    private Library library;
}
