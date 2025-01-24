package com.example.library.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class BookResponce {
    private String title;
    @JsonProperty("author")
    private AuthorResponce authorResponce;
    private String description;
    private Float price;
}
