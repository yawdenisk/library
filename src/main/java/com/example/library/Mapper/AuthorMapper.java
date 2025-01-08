package com.example.library.Mapper;

import com.example.library.Entities.Author;
import com.example.library.Entities.AuthorResponce;
import org.mapstruct.Mapper;

@Mapper
public interface AuthorMapper {
    AuthorResponce AuthorToAuthorResponce(Author author);
}
