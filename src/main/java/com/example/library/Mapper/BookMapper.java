package com.example.library.Mapper;

import com.example.library.Entities.Book;
import com.example.library.Entities.BookResponce;
import org.mapstruct.Mapper;

@Mapper
public interface BookMapper {
    BookResponce BookToBookResponce(Book book);
}
